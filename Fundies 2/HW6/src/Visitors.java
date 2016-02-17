import tester.Tester;

// Assignment 6
//Zhang Xi
//xizhang2
//Yifan Xing
//xingyif

// to represent an interface class 
interface IArith {
    // represent the interface accept the visitor
    <R> R accept(IArithVisitor<R> visitor);

}

// to represent a Const class
class Const implements IArith {
    double num;
    // the constructor
    Const(double num) {
        this.num = num;
    }

    // represent the const class accept the visitor
    public <R> R accept(IArithVisitor<R> visitor) {
        return visitor.visitConst(this);
    }
}

// to represent a Formula class
class Formula implements IArith {
    IFunc2<Double, Double, Double> fun;
    String name;
    IArith left;
    IArith right;
    // the constructor
    Formula(IFunc2<Double, Double, Double> fun, String name, IArith left, IArith right) {
        this.fun = fun;
        this.name = name;
        this.left = left;
        this.right = right;
    }

    // represent the formula class accept the visitor
    public <R> R accept(IArithVisitor<R> visitor) {
        return visitor.visitFormula(this);
    }

}

// to represent a method interface
interface IFunc2<A1, A2, R> {
    R apply(A1 a1, A2 a2);
}

// to represent the divide method 
class Divide implements IFunc2<Double, Double, Double> {
    // to apply division to two Doubles
    public Double apply(Double d1, Double d2) {
        return d1 / d2;
    }
}

// to represent the multiply method
class Multiply implements IFunc2<Double, Double, Double> {
    // to apply multiplication to two Doubles
    public Double apply(Double d1, Double d2) {
        return d1 * d2;
    }
}

// to represent the plus method
class Plus implements IFunc2<Double, Double, Double> {
    // to apply addition to two Doubles
    public Double apply(Double d1, Double d2) {
        return d1 + d2;
    }
}


// represent a visitor that visits an IArith and produce
// a result of type R
interface IArithVisitor<R> {
    // visit the constant class
    R visitConst(Const c);
    // visit the formula class
    R visitFormula(Formula f);
}


// to represent a visitor class that visits an IArith
// and evaluates the tree to a Double answer
class EvalVisitor implements IArithVisitor<Double> {
    // visit the constant class
    public Double visitConst(Const c) {
        return c.num;
    }
    // visit the formula class
    public Double visitFormula(Formula f) {
        return f.fun.apply(f.left.accept(this), f.right.accept(this));
    }
}

// to produce a String showing the fully-parenthesized expression
class PrintVisitor implements IArithVisitor<String> {
    // visit the constant class
    public String visitConst(Const c) {
        return Double.toString(c.num);
    }
    // visit the formula class
    public String visitFormula(Formula f) {
        return "(" + f.name + " " + f.left.accept(this) + 
                " " + f.right.accept(this) + ")";
    }
}

// to double every constant in the IArith
class DoublerVisitor implements IArithVisitor<IArith> {
    // visit the constant class
    public IArith visitConst(Const c) {
        return new Const(c.num * 2);
    }
    // visit the formula class
    public IArith visitFormula(Formula f) {
        return new Formula(f.fun, f.name,
                f.left.accept(this), f.right.accept(this));
    }
}

// to determine if every constant in the tree is less than 10
class AllSmallVisitor implements IArithVisitor<Boolean> {
    // visit the constant class
    public Boolean visitConst(Const c) {
        return c.num < 10;
    }
    // visit the formula class
    public Boolean visitFormula(Formula f) {
        return f.left.accept(this) && f.right.accept(this);
    }
}

// to produce a boolean to make sure that the right argument
// in a division formula is not equal to 0
class NoDivBy0 implements IArithVisitor<Boolean> {
    // visit the constant class
    public Boolean visitConst(Const c) {
        return true;
    }
    // visit the formula class
    public Boolean visitFormula(Formula f) {
        if (f.name.equals("div")) {
            return f.left.accept(this) &&
                    f.right.accept(this) &&
                    Math.abs(f.right.accept(new EvalVisitor())) >= 0.0001;
        }
        else {
            return f.left.accept(this) && 
                    f.right.accept(this);
        }
    }
}

// represent an example class
class ExamplesVisitor {

    IFunc2<Double, Double, Double> div = new Divide();
    IFunc2<Double, Double, Double> mul = new Multiply();
    IFunc2<Double, Double, Double> plus = new Plus();

    IArithVisitor<Double> eval = new EvalVisitor();
    IArithVisitor<String> print = new PrintVisitor();
    IArithVisitor<IArith> doubler = new DoublerVisitor();
    IArithVisitor<Boolean> small = new AllSmallVisitor();
    IArithVisitor<Boolean> noDivBy0 = new NoDivBy0();

    IArith const1 = new Const(10.0);
    IArith const2 = new Const(20.0);
    IArith const3 = new Const(4.0);
    IArith const4 = new Const(0.0);
    IArith formula1 = new Formula(div, "div", this.const1, this.const2);
    IArith formula2 = new Formula(mul, "mul", this.const1, this.const2);
    IArith formula3 = new Formula(plus, "plus", this.formula1, this.const1);
    IArith formula4 = new Formula(plus, "plus", this.formula1, this.formula2);
    IArith formula5 = new Formula(div, "div", this.const2, 
            new Const(40.0));
    IArith formula6 = new Formula(plus, "plus", this.const3, this.const3);
    IArith formula7 = new Formula(div, "div", this.formula6, this.const3);
    IArith formula8 = new Formula(div, "div", this.formula1, this.const4);
    IArith formula9 = new Formula(div, "div", this.formula5, this.formula8);


    // test the arithmetic method 
    boolean testArithmetic(Tester t) { 
        return t.checkExpect(this.div.apply(10.0, 1.0), 10.0) &&
                t.checkExpect(this.mul.apply(10.0, 2.0), 20.0) &&
                t.checkExpect(this.plus.apply(15.0, 5.0), 20.0);
    }
    
    
    // test the method EvalVisitor()
    boolean testEvalVisitor(Tester t) {
        return t.checkExpect(this.const1.accept(eval), 10.0) &&
                t.checkExpect(this.const2.accept(eval), 20.0) &&
                t.checkExpect(this.formula1.accept(eval), 0.5) &&
                t.checkExpect(this.formula2.accept(eval), 200.0);

    }

    // test the method PrintVisitor()
    boolean testPrintVisitor(Tester t) {
        return t.checkExpect(this.const1.accept(print), "10.0") &&
                t.checkExpect(this.const2.accept(print), "20.0") &&
                t.checkExpect(this.formula1.accept(print), 
                        "(div 10.0 20.0)") &&
                t.checkExpect(this.formula2.accept(print), 
                        "(mul 10.0 20.0)") &&
                t.checkExpect(this.formula3.accept(print),
                        "(plus (div 10.0 20.0) 10.0)") &&
                t.checkExpect(this.formula4.accept(print),
                        "(plus (div 10.0 20.0) (mul 10.0 20.0))");
    }

    // test the method DoublerVisitor()
    boolean testDoublerVisitor(Tester t) {
        return t.checkExpect(this.const1.accept(doubler), new Const(20.0)) &&
                t.checkExpect(this.const2.accept(doubler), new Const(40.0)) &&
                t.checkExpect(this.formula1.accept(doubler), this.formula5) &&
                t.checkExpect(this.formula3.accept(doubler),
                        new Formula(plus, "plus", this.formula5, new Const(20.0)));
    }

    // test the method AllSmallVisitor()
    boolean testAllSmallVisitor(Tester t) {
        return t.checkExpect(this.const3.accept(small), true) &&
                t.checkExpect(this.const1.accept(small), false) &&
                t.checkExpect(this.formula1.accept(small), false) &&
                t.checkExpect(this.formula7.accept(small), true);

    }
    
    // test the method NoDiv0()
    boolean testNoDiv0(Tester t) {
        return t.checkExpect(this.const1.accept(noDivBy0), true) &&
                t.checkExpect(this.const2.accept(noDivBy0), true) &&
                t.checkExpect(this.const4.accept(noDivBy0), true) &&
                t.checkExpect(this.formula8.accept(noDivBy0), false) &&
                t.checkExpect(this.formula9.accept(noDivBy0), false);
    }
}
