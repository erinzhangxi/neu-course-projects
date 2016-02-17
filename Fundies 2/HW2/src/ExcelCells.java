import tester.*;

// Zhang Xi
// xizhang2
// Laniado Jonathan
// laniadoj

class Cell {
    String column;
    int row;
    IData data;

    Cell(String column, int row, IData data) {
        this.column = column;
        this.row = row;
        this.data = data;
    }
    
    // to compute the value of this cell
    int value() {
        return this.data.value();
    }

    // to compute the number of cells that contain numbers involved in computing
    // the value of this Cell
    int countArgs() {
        return this.data.countArgs();
    }

    // to compute the number of function applications involved in computing the
    // value of this Cell
    int countFuns() {
        return this.data.countFuns();
    }

    // to compute how deeply this IData is nested in this cell
    int formulaDepth() {
        return this.data.formulaDepth();
    }

    // to produce a string representing the contents for this IData 
    // given an integer depth that cannot be less than 0
    String formula(int depth) {
        if (depth <= 0) {
            return this.column + Integer.toString(this.row);
        } 
        else {
            return this.data.formula(depth);
        }

    }
}

interface IData {
    // to compute the value of this cell
    int value();

    // to compute the number of cells that contain numbers involved in computing
    // the value of this Cell
    int countArgs();

    // to compute the number of function applications involved in computing the
    // value of this Cell
    int countFuns();

    // to compute how deeply this IData is nested in this cell
    int formulaDepth();

    // helper method for forumlaDepth
    int formulaDepthHelper();

    // to produce a string representing the contents for this IData 
    // given an integer depth that cannot be less than 0
    String formula(int depth);

}

class Number implements IData {
    int number;

    Number(int number) {
        this.number = number;
    }

    // to compute the value of this Number
    public int value() {
        return this.number;
    }

    // to compute the number of cells that contain numbers involved in computing
    // the value of this Number cell
    public int countArgs() {
        return 1;
    }

    // to compute the number of function applications involved in computing the
    // value of this Number
    public int countFuns() {
        return 0;
    }

    // to compute how deeply this IData is nested in this cell
    public int formulaDepth() {
        return 0;
    }

    // helper method for formulaDepth
    public int formulaDepthHelper() {
        return 0;
    }

    // to produce a string representing the contents for this Number given an integer depth
    public String formula(int depth) {
        return Integer.toString(number);
    }
}

class Formula implements IData {
    AFunction func;

    public Formula(AFunction func) {
        this.func = func;
    }

    // to compute the value of this Formula
    public int value() {
        return this.func.value();
    }

    // to compute the number of cells that contain numbers involved in computing
    // the value of this Formula cell
    public int countArgs() {
        return this.func.countArgs();
    }

    // to compute the number of function applications involved in computing the
    // value of this Formula
    public int countFuns() {
        return this.func.countFuns(); 
    }

    // to compute how deeply this IData is nested in this cell
    public int formulaDepth() {
        return this.func.formulaDepthHelper();
    }

    // helper method for formulaDepth
    public int formulaDepthHelper() {
        return 1 + this.func.formulaDepthHelper();
    }

    // to produce a string representing the contents for this Formula given an integer depth
    public String formula(int depth) {
        return this.func.formula(depth);
    }

}

interface IFunction {
   
}

abstract class AFunction implements IFunction {
    Cell lt;
    Cell rt;
    
    AFunction(Cell lt, Cell rt) {
        this.lt = lt;
        this.rt = rt;
    }
    
    // to compute the value of this Function
    abstract int value();

    // to compute the number of cells that contain numbers involved in computing
    // the value of this Function
    int countArgs() {
        return this.lt.countArgs() + this.rt.countArgs();
    }
    
    // to compute the number of function applications involved in computing the
    // value of this Function
    int countFuns() {
        return 1 + this.lt.countFuns() + this.rt.countFuns();
    }

    // to compute how deeply this IData is nested in this cell
    int formulaDepth() {
        return Math.max(this.lt.formulaDepth(), this.rt.formulaDepth());
    }

    // helper method for formulaDepth
    int formulaDepthHelper() {
        return 1 + this.formulaDepth();
    }

    // to produce a string representing the contents for this Function given an integer depth
    abstract String formula(int depth);
}

class Mod extends AFunction {

    Mod(Cell lt, Cell rt) {
        super(lt, rt);
    }

    // to compute the value of this mod cell
    public int value() {
        return this.lt.value() % this.rt.value();
    }

    // to produce a string representing the contents for this mod cell given an integer depth
    public String formula(int depth) {
        return "mod(" + this.lt.formula(depth - 1) + ", " + this.rt.formula(depth - 1) + ")";
    }
}

class Mul extends AFunction {

    Mul(Cell lt, Cell rt) {
        super(lt, rt);
    }

    // to compute the value of this mul cell
    public int value() {
        return this.lt.value() * this.rt.value();
    }

    // to produce a string representing the contents for this mul cell given an integer depth
    public String formula(int depth) {
        return "mul(" + this.lt.formula(depth - 1) + ", " + this.rt.formula(depth - 1) + ")";
    }    
}

class Sub extends AFunction {

    Sub(Cell lt, Cell rt) {
        super(lt, rt);
    }

    // to compute the value of this sub cell
    public int value() {
        return this.lt.value() - this.rt.value();
    }

    // to produce a string representing the contents for this sub cell given an integer depth
    public String formula(int depth) {
        return "sub(" + this.lt.formula(depth - 1) + ", " + this.rt.formula(depth - 1) + ")";
    }
}

class ExamplesExcelCells {
    Cell cellA1 = new Cell("A", 1, new Number(13));
    Cell cellB1 = new Cell("B", 1, new Number(5));
    Cell cellC1 = new Cell("C", 1, new Number(2));
    Cell cellD1 = new Cell("D", 1, new Number(3));
    Cell cellE1 = new Cell("E", 1, new Number(7));
    Cell cellA3 = new Cell("A", 3, new Formula(new Mul(this.cellA1, this.cellB1)));
    Cell cellC4 = new Cell("C", 4, new Formula(new Mul(this.cellE1, this.cellD1)));
    Cell cellE2 = new Cell("E", 2, new Formula(new Sub(this.cellE1, this.cellD1)));
    Cell cellB2 = new Cell("B", 2, new Formula(new Sub(this.cellA3, this.cellC1)));
    Cell cellB3 = new Cell("B", 3, new Formula(new Mod(this.cellE1, this.cellA3)));
    Cell cellD2 = new Cell("D", 2, new Formula(new Mod(this.cellB2, this.cellE2)));
    Cell cellD3 = new Cell("D", 3, new Formula(new Mul(this.cellD2, this.cellA1)));
    Cell cellD4 = new Cell("D", 4, new Formula(new Sub(this.cellC4, this.cellA1)));
    Cell cellC5 = new Cell("C", 5, new Formula(new Sub(this.cellD4, this.cellB3)));
    Cell cellA5 = new Cell("A", 5, new Formula(new Mod(this.cellD3, this.cellC5)));    

    boolean testValue(Tester t) {
        return t.checkExpect(this.cellA1.value(), 13) &&
                t.checkExpect(this.cellB2.value(), 63) &&
                t.checkExpect(this.cellB3.value(), 7) &&
                t.checkExpect(this.cellA5.value(), 0) &&
                t.checkExpect(this.cellA3.value(), 65);
    }

    boolean testCountArgs(Tester t) {
        return t.checkExpect(this.cellB1.countArgs(), 1) &&
                t.checkExpect(this.cellA3.countArgs(), 2) &&
                t.checkExpect(this.cellC5.countArgs(), 6) &&
                t.checkExpect(this.cellD2.countArgs(), 5);
    }

    boolean testCountFuns(Tester t) {
        return t.checkExpect(this.cellD2.countFuns(), 4) &&
                t.checkExpect(this.cellA5.countFuns(), 11) &&
                t.checkExpect(this.cellA1.countFuns(), 0);
    }

    boolean testFormulaDepth(Tester t) {
        return t.checkExpect(this.cellD2.formulaDepth(), 3) &&
                t.checkExpect(this.cellE2.formulaDepth(), 1) &&
                t.checkExpect(this.cellD3.formulaDepth(), 4);
    } 

    boolean testFormulaDepthHelper(Tester t) {
        return t.checkExpect(new Number(100).formulaDepthHelper(), 0) && 
                t.checkExpect(new Formula(new Sub(this.cellA1, 
                        this.cellB1)).formulaDepthHelper(), 2);
    }

    boolean testFormula(Tester t) {
        return t.checkExpect(this.cellD2.formula(0), "D2") &&
                t.checkExpect(this.cellD2.formula(2), "mod(sub(A3, C1), sub(E1, D1))") &&
                t.checkExpect(this.cellD2.formula(4), "mod(sub(mul(13, 5), 2), sub(7, 3))");
    }
}