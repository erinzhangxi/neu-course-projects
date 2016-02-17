// CS 2510 Fall 2015
// Assignment 3

// Zhang Xi
// xizhang2
// Laniado Jonathan
// laniadoj

/*
 * 
import tester.*;
import javalib.colors.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;

interface IMobile {

    // to compute the total weight of this Mobile
    int totalWeight();

    // to compute the total height of this Mobile
    int totalHeight();

    // to determine if this Mobile is balanced 
    boolean isBalanced();

    // given the length of the string and the strut, to produce a new 
    // Mobile which combines this Mobile and the given Mobile 
    IMobile buildMobile(IMobile that, int String, int strut);

    // to compute the current width of this mobile
    int curWidth();

    // to compute the left side of the current width of this Complex mobile
    int leftWidth();

    // to compute the right side of the current width of this Complex mobile
    int rightWidth();

    // to produce the image of this Mobile at the given position
    WorldImage drawMobile(Posn n);
}

// a simple mobile class
class Simple implements IMobile {
    int length;
    int weight;
    IColor color;

    // constructor for this Simple mobile class
    Simple(int length, int weight, IColor color) {
        this.length = length;
        this.weight = weight;
        this.color = color;
    }

    // to compute the total weight of this Simple mobile
    public int totalWeight() {
        return this.weight;
    }

    // to compute the total height of the mobile
    public int totalHeight() {
        return this.length + (this.weight / 10);
    }

    // to determine if this Simple mobile is balanced 
    public boolean isBalanced() {
        return true;
    }

    // given the length of the string and the strut, to produce a new 
    // Mobile which combines this Mobile and the given Mobile 
    public IMobile buildMobile(IMobile that, int String, int strut) {
        return new Complex(String, 
                that.totalWeight() * strut / (that.totalWeight() + this.totalWeight()),
                this.totalWeight() * strut / (that.totalWeight() + this.totalWeight()),
                this,
                that);
    }

    // to compute the current width of this Simple mobile
    public int curWidth() {
        if(this.weight % 10 == 0) {
            return this.weight / 10;
        }
        else {
            return this.weight / 10 + 1;
        }
    }

    // to compute the left side of the current width of this Complex mobile
    public int leftWidth() {
        if(this.weight % 20 == 0) {
            return this.weight / 20;
        }
        else {
            return this.weight / 20 + 1;
        }
    }

    // to compute the right side of the current width of this Complex mobile
    public int rightWidth() {
        if(this.weight % 20 == 0) {
            return this.weight / 20;
        }
        else {
            return this.weight / 20 + 1;
        }
    }

    // to produce the image of this Mobile at the given position
    public WorldImage drawMobile(Posn p) {

        WorldImage lineLength = new LineImage(new Posn(p.x, p.y), 
                new Posn(p.x, p.y + this.length), new Black()); 

        WorldImage rectangle = new RectangleImage
                (new Posn(p.x, p.y + this.length + this.weight / 2), 
                        this.weight, this.weight, this.color);
         
        return lineLength.overlayImages(rectangle);
    }
}

// a complex mobile class
class Complex implements IMobile {
    int length;
    int leftside;
    int rightside;
    IMobile left;
    IMobile right;

    // constructor for this Complex mobile class
    Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
        this.length = length;
        this.leftside = leftside;
        this.rightside = rightside;
        this.left = left;
        this.right = right;
    }

    // to compute the total weight of this Complex mobile
    public int totalWeight() {
        return this.left.totalWeight() + this.right.totalWeight();
    }

    // to compute the total height of this Complex mobile
    public int totalHeight() {
        return this.length + Math.max(this.left.totalHeight(), this.right.totalHeight());
    }

    // to determine if this Complex mobile is balanced 
    public boolean isBalanced() {
        return (this.left.totalWeight() * this.leftside) == (this.right.totalWeight() * this.rightside);               
    }

    // given the length of the string and the strut, to produce a new 
    // Mobile which combines this Mobile and the given Mobile 
    public IMobile buildMobile(IMobile that, int String, int strut) {
        return new Complex(String, 
                that.totalWeight() * strut / (this.totalWeight() + that.totalWeight()),
                strut - (that.totalWeight() * strut / (this.totalWeight() + that.totalWeight())),
                this,
                that); 
    }

    // to compute the current width of this Complex mobile
    public int curWidth() {
        return this.leftside + this.rightside + this.left.leftWidth() + this.right.rightWidth();    
    }

    // to compute the left side of the current width of this Complex mobile
    public int leftWidth() {
        return this.leftside + this.left.leftWidth();
    }

    // to compute the right side of the current width of this Complex mobile
    public int rightWidth() {
        return this.rightside + this.right.rightWidth();
    }

    // to produce the image of this Mobile at the given position
    public WorldImage drawMobile(Posn p) {

        WorldImage strut = new LineImage(new Posn(p.x - this.leftside, p.y + this.length), 
                new Posn(p.x + this.rightside, p.y + this.length), 
                new Black());

        WorldImage rod = new LineImage(new Posn(p.x, p.y), 
                new Posn(p.x, p.y + this.length),
                new Black());

        return rod.overlayImages(strut, 
                this.left.drawMobile(new Posn(p.x - this.leftside, p.y + this.length)),
                this.right.drawMobile(new Posn(p.x + this.rightside, p.y + this.length))); 
    }
}


// an example class
class ExamplesMobiles2 {
    IColor blue = new Blue();
    IColor red = new Red();
    IColor green = new Green();

    IMobile exampleSimple = new Simple(2, 20, this.blue);

    IMobile exampleComplex = new Complex(1, 9, 3, new Simple(1, 36, this.blue),
            new Complex(2, 8, 1, new Simple(1, 12, this.red), 
                    new Complex(2, 5, 3, new Simple(2, 36, this.red), new Simple(1, 60, this.green))));

    IMobile example3 = new Complex(1, 7, 4, new Complex(2, 4, 4, new Simple(1, 40, this.blue),
            new Complex(3, 2, 1, new Simple(2, 13, this.blue), new Simple(1, 24, this.red))),
            new Complex(1, 2, 4, new Simple(1, 20, this.green), new Complex(2, 1, 2, new Simple(3, 60, this.green),
                    new Complex(1, 2, 3, new Simple(1, 8, this.red), new Simple(1, 20, this.blue)))));

    IMobile simple2 = new Simple(1, 40, this.red);   

    IMobile simple3 = new Simple(30, 50, this.blue);

    IMobile drawSimple1 = new Simple(70, 70, this.red);

    IMobile easyComplex1 = new Complex(1, 3, 2, new Simple(1, 30, this.green), 
            new Simple(1, 45, this.red));

    IMobile easyComplex2 = new Complex(1, 4, 2, new Simple(1, 15, this.red),
            new Simple(1, 30, this.green));

    IMobile drawComplex1 = new Complex(30, 90, 30, new Simple(30, 46, this.blue),
            new Complex(60, 80, 10, new Simple(30, 22, this.red), 
                    new Complex(60, 50, 30, new Simple(60, 46, this.red), new Simple(30, 70, this.green))));

    // to test the method totalWeight of the IMobile class
    boolean testTotalWeight(Tester t) {
        return t.checkExpect(this.exampleSimple.totalWeight(), 20)
                && t.checkExpect(this.exampleComplex.totalWeight(), 144)
                && t.checkExpect(this.example3.totalWeight(), 185);
    }

    // to test the method totalHeight of the IMobile class
    boolean testTotalHeight(Tester t) {
        return t.checkExpect(this.exampleSimple.totalHeight(), 4)
                && t.checkExpect(this.exampleComplex.totalHeight(), 12);
    }

    // to test the method isBalanced of the IMobile class
    boolean testIsBalanced(Tester t) {
        return t.checkExpect(this.exampleSimple.isBalanced(), true)
                && t.checkExpect(this.exampleSimple.isBalanced(), true)
                && t.checkExpect(this.example3.isBalanced(), false);
    }

    // to test the method buildMobile of the IMobile class
    boolean testBuildMobile(Tester t) {
        return t.checkExpect(this.exampleSimple.buildMobile(this.simple2, 5, 3),
                new Complex(5, 2, 1, this.exampleSimple, this.simple2))
                && t.checkExpect(this.easyComplex1.buildMobile(this.easyComplex2, 1, 8),
                        new Complex(1, 3, 5, this.easyComplex1, this.easyComplex2));
    }

    // to test the method curWidth of the IMobile class
    boolean testCurWidth(Tester t) {
        return t.checkExpect(this.exampleSimple.curWidth(), 2)
                && t.checkExpect(this.simple2.curWidth(), 4)
                && t.checkExpect(new Simple(1, 48, this.red).curWidth(), 5)
                && t.checkExpect(this.exampleComplex.curWidth(), 21);
    }

    // display the image on the canvas (no real tests are possible)
    boolean testDrawImage(Tester t) {
        WorldCanvas c = new WorldCanvas(500, 500);
        return 
                t.checkExpect(
                        c.show() &&
                        c.drawImage(this.exampleSimple.drawMobile(new Posn(200, 200))), true)

                &&   
                t.checkExpect(
                        c.show() &&
                        c.drawImage(this.exampleSimple.drawMobile(new Posn(200, 10))), true)
                && 
                t.checkExpect(
                        c.show() &&
                        c.drawImage(this.drawComplex1.drawMobile(new Posn(200, 10))), true);           
    }
}  


*/
