// Assignment 3
// laniado jonathan
// laniadoj
// zhang xi
// xizhang2

import javalib.colors.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;
import tester.*;

// represents an IMobile
interface IMobile {
    // computes the total weight of this IMobile
    int totalWeight();

    // computes the total height of this IMobile
    int totalHeight();

    // determines whether this IMobile is balanced
    boolean isBalanced();

    // combines this balanced IMobile with that balanced IMobile
    IMobile buildMobile(int length, int strut, IMobile that);

    // computes the total current width of this IMobile
    int curWidth();

    // computes the current width of this IMobile's left IMobile
    public int curWidthLeft();

    // computes the current width of this IMobile's right IMobile
    public int curWidthRight();

    // produces the image of this IMobile 
    public WorldImage drawMobile(Posn p);
}

// represents a Simple IMobile
class Simple implements IMobile {
    int length;
    int weight;
    IColor color;

    Simple(int length, int weight, IColor color) {
        this.length = length;
        this.weight = weight;
        this.color = color;
    }

    // computes the total weight of this Simple IMobile
    public int totalWeight() {
        return this.weight;
    }

    // computes the total height of this Simple IMobile
    public int totalHeight() {
        return this.length + (int) (this.weight / 10);
    }

    // determines whether this Simple IMobile is balanced
    public boolean isBalanced() {
        return true;
    }

    // combines this balanced Simple IMobile with that balanced IMobile
    public IMobile buildMobile(int thatLength, int strut, IMobile that) {
        int len = (that.totalWeight() * strut) / 
                (this.totalWeight() + that.totalWeight());
        return new Complex(thatLength, len, strut - len, this, this);
    }

    // computes the total current width of this Simple IMobile
    public int curWidth() {
        return (int) Math.ceil((double) this.weight / 10);
    }

    // computes the current width of this IMobile's left IMobile
    public int curWidthLeft() {
        return (int) Math.ceil((double) this.weight / 20);
    }

    // computes the current width of this IMobile's right IMobile
    public int curWidthRight() {
        return (int) Math.ceil((double) this.weight / 20);
    }

    // produces the image of this Simple IMobile
    public WorldImage drawMobile(Posn p) {

        WorldImage strut = new LineImage(
                new Posn(p.x, p.y), new Posn(p.x, p.y + length), new Black());

        WorldImage rect = new RectangleImage(new 
                Posn(p.x, p.y + length + (this.weight / 2)),
                this.weight, this.weight, this.color);

        return strut.overlayImages(rect);
    }
}

// represents a Complex IMobile
class Complex implements IMobile {
    int length;
    int leftside;
    int rightside;
    IMobile left;
    IMobile right;

    Complex(int length, int leftside, int rightside, 
            IMobile left, IMobile right) {
        this.length = length;
        this.leftside = leftside;
        this.rightside = rightside;
        this.left = left;
        this.right = right;
    }

    // computes the total weight of this Complex IMobile
    public int totalWeight() {
        return this.left.totalWeight() + this.right.totalWeight();
    }

    // computes the total height of this Complex IMobile
    public int totalHeight() {
        return this.length + 
                Math.max(this.left.totalHeight(), this.right.totalHeight());
    }

    // determines whether this Complex IMobile is balanced
    public boolean isBalanced() {
        return (this.left.totalWeight() * this.leftside) ==
                (this.right.totalWeight() * this.rightside) && 
                left.isBalanced() & right.isBalanced();
    }

    // combines this balanced Complex IMobile with that balanced IMobile
    public IMobile buildMobile(int thatLength, int strut, IMobile that) {
        int len = (that.totalWeight() * strut) / 
                (this.totalWeight() + that.totalWeight());
        return new Complex(thatLength, len, strut - len, this, that);
    }

    // computes the total current width of this Complex IMobile
    public int curWidth() {
        return this.leftside + this.rightside + 
                this.left.curWidthLeft() + this.right.curWidthRight();
    }

    // computes the current width of this IMobile's left IMobile
    public int curWidthRight() {
        return this.rightside + this.right.curWidthRight();
    }

    // computes the current width of this IMobile's right IMobile
    public int curWidthLeft() {
        return this.leftside + this.left.curWidthLeft(); 
    }

    // produces the image of this Complex IMobile
    public WorldImage drawMobile(Posn p) {

        WorldImage rod = new LineImage(p,
                new Posn(p.x, p.y + this.length), new Black());

        WorldImage strut = new LineImage(
                new Posn(p.x - this.leftside, p.y + this.length),
                new Posn(p.x + this.rightside, p.y + this.length), 
                new Black());

        return rod.overlayImages(
                strut, 
                this.left.drawMobile(
                        new Posn(p.x - this.leftside, p.y + this.length)),
                this.right.drawMobile(
                        new Posn(p.x + this.rightside, p.y + this.length)));
    }
}

// examples and tests
class ExamplesMobiles {
    IColor blue = new Blue();
    IColor red = new Red();
    IColor green = new Green();

    // examples
    IMobile exampleSimple = new Simple(2, 20, this.blue);

    IMobile exampleComplex = 
            new Complex(1, 9, 3, 
                    new Simple(1, 36, this.blue),
                    new Complex(2, 8, 1, 
                            new Simple(1, 12, this.red), 
                            new Complex(2, 5, 3, 
                                    new Simple(2, 36, this.red), 
                                    new Simple(1, 60, this.green))));

    IMobile example3 = 
            new Complex(2, 2, 2, this.exampleComplex, this.exampleComplex);

    IMobile exampleSimpleBuildMobile = 
            new Complex(5, 10, 10, this.exampleSimple, this.example3);

    IMobile exampleComplexBuildMobile = 
            new Complex(6, 12, 12, this.exampleComplex, this.example3);

    IMobile exampleSimpleDrawMobile = 
            new Simple(50, 50, this.red);

    IMobile exampleComplexDrawMobile1 = 
            new Complex(50, 50, 50, 
                    new Simple(150, 30, this.red), 
                    new Simple(150, 30, this.blue));

    IMobile exampleComplexDrawMobile2 = 
            new Complex(30, 90, 30, 
                    new Simple(30, 46, this.blue),
                    new Complex(60, 80, 10, 
                            new Simple(30, 22, this.red), 
                            new Complex(60, 50, 30, 
                                    new Simple(60, 46, this.red), 
                                    new Simple(30, 70, this.green))));

    // tests the totalWeight method
    boolean testTotalWeight(Tester t) {
        return t.checkExpect(this.exampleSimple.totalWeight(), 20)
                && t.checkExpect(this.exampleComplex.totalWeight(), 144)
                && t.checkExpect(this.example3.totalWeight(), 288)
                && t.checkExpect(this.exampleComplexBuildMobile.totalWeight(), 
                        432)
                && t.checkExpect(this.exampleComplexDrawMobile1.totalWeight(), 
                        60)
                && t.checkExpect(this.exampleComplexDrawMobile2.totalWeight(), 
                        184)
                && t.checkExpect(this.exampleSimpleBuildMobile.totalWeight(), 
                        308)
                && t.checkExpect(this.exampleSimpleDrawMobile.totalWeight(), 
                        50);
    }

    // tests the totalHeight method
    boolean testTotalHeight(Tester t) {
        return t.checkExpect(this.exampleSimple.totalHeight(), 4)
                && t.checkExpect(this.exampleComplex.totalHeight(), 12)
                && t.checkExpect(this.example3.totalHeight(), 14)
                && t.checkExpect(this.exampleComplexBuildMobile.totalHeight(), 
                        20)
                && t.checkExpect(this.exampleComplexDrawMobile1.totalHeight(), 
                        203)
                && t.checkExpect(this.exampleComplexDrawMobile2.totalHeight(), 
                        214)
                && t.checkExpect(this.exampleSimpleBuildMobile.totalHeight(), 
                        19)
                && t.checkExpect(this.exampleSimpleDrawMobile.totalHeight(), 
                        55);
    }

    // tests the isBalanced method
    boolean testIsBalanced(Tester t) {
        return t.checkExpect(this.exampleSimple.isBalanced(), true)
                && t.checkExpect(this.exampleComplex.isBalanced(), true)
                && t.checkExpect(this.example3.isBalanced(), true)
                && t.checkExpect(this.exampleSimpleBuildMobile.isBalanced(), 
                        false)
                && t.checkExpect(this.exampleComplexBuildMobile.isBalanced(), 
                        false)
                && t.checkExpect(this.exampleComplexDrawMobile1.isBalanced(),
                        true)
                && t.checkExpect(this.exampleComplexDrawMobile2.isBalanced(),
                        false)
                && t.checkExpect(this.exampleSimpleDrawMobile.isBalanced(),
                        true);
    }

    // tests the buildMobile method
    boolean testBuildMobile(Tester t) {
        return t.checkExpect(this.exampleComplex.buildMobile(4, 150, 
                this.example3),
                new Complex(4, 100, 50, this.exampleComplex, this.example3)) &&
                t.checkExpect(
                        this.exampleSimple.buildMobile(10, 10, 
                                this.exampleSimple),
                        new Complex(10, 5, 5, this.exampleSimple, 
                                this.exampleSimple));
    }

    // tests the curWidth method
    boolean testCurWidth(Tester t) {
        return t.checkExpect(this.exampleSimple.curWidth(), 2)
                && t.checkExpect(new Simple(2, 20, new Red()).curWidth(), 2)
                && t.checkExpect(this.exampleComplex.curWidth(), 21)
                && t.checkExpect(this.example3.curWidth(), 25)
                && t.checkExpect(this.exampleSimpleBuildMobile.curWidth(), 33)
                && t.checkExpect(this.exampleComplexBuildMobile.curWidth(), 47);
    }

    // display the image on the canvas (no real tests are possible)
    boolean drawImageTest(Tester t) {
        WorldCanvas c1 = new WorldCanvas(400, 300);
        WorldCanvas c2 = new WorldCanvas(400, 300);
        WorldCanvas c3 = new WorldCanvas(400, 300);
        return t.checkExpect(
                c1.show() &&
                c1.drawImage(this.exampleSimple.drawMobile(
                        new Posn(200, 10))), true) &&
                t.checkExpect(
                        c2.show() &&
                        c2.drawImage(this.exampleComplex.drawMobile(
                                new Posn(200, 10))), true) &&
                t.checkExpect(
                        c3.show() &&
                        c3.drawImage(this.example3.drawMobile(
                                new Posn(200, 10))), true); 
    }
}