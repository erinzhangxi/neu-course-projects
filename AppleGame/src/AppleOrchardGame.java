// Assignment 5

// Zhang Xi

// xizhang2

// Yifan Xing

// xingyif

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.util.Random; 

// to represent the apple class
// x and y are positive integers representing its location
class Apple {
    int x;
    int y;
    String type;
    Random r = new Random();

    int appleSize = 60;

    // the constructor
    Apple(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // convenience constructor                     
    Apple(String type) {
        this.x = r.nextInt(400); 
        this.y = 0;
        this.type = type;
    }
    
    

    // produce the apple image                    
    public WorldImage appleImage() {
        if (this.type.equals("red apple")) {
            return new FromFileImage(new Posn(this.x, this.y),
                    "red-apple.png");
        }
        else if (this.type.equals("small red apple")) {
            return new FromFileImage(new Posn(this.x, this.y),
                    "small-red-apple.png");
        }
        else if (this.type.equals("yellow apple")) {
            return new FromFileImage(new Posn(this.x, this.y),
                    "yellow-apple.png");
        }
        else {
            throw new IllegalArgumentException("Image" + this.type
                    + " not found.");

        }
    }

    // produces a new apple that fallen down by a fixed amount
    Apple moveDown(int num) {
        return new Apple(this.x, this.y + num, this.type);
    }

    // to determine whether this Apple is on the ground
    boolean onTheGround() {
        return this.y >= 400 - appleSize / 2;
    }

    // to produce a new Apple on the next tick
    Apple fall() {
        if (this.y < 400) {
            return new Apple(this.x, this.y + 10, this.type);
        }
        else {
            return new Apple(this.type);
        }
    }     

    // to determine whether an apple has fallen 
    // into a basket
    boolean caughtApple(Apple a, Basket b) {              
        if ((b.x - b.width / 2 <= a.x &&
                a.x <= b.x + b.width / 2) && a.y == b.y - a.appleSize) {
            a.y += a.appleSize;
            return true;
        }
        else {
            return false;
        }
    }
}

// to represent the interface ILoApple
interface ILoApple {
    // to produce a list of apples
    ILoApple randomLoApple();

    // to determine whether an apple has fallen 
    // into a basket
    boolean caughtApple(Basket b);

    // to draw a list of apples
    WorldImage loAppleImage();

}

// to represent the class MtLoApple, empty list of apple
class MtLoApple implements ILoApple {
    // to produce a list of apples
    public ILoApple randomLoApple() {
        return this;
    }

    // to determine whether an apple has fallen 
    // into a basket
    public boolean caughtApple(Basket b) {
        return false;
    }

    // to draw a circle with radius of 0 to represent an empty list of apples
    public WorldImage loAppleImage() {
        return new CircleImage(new Posn(0, 0), 0, new White());
    }

}

// to represent the class ConsLoApple, a cons list of apple
class ConsLoApple implements ILoApple {
    Apple first;
    ILoApple rest;
    // the constructor
    ConsLoApple(Apple first, ILoApple rest) {
        this.first = first;
        this.rest = rest;
    }

    // to produce a list of apples
    public ILoApple randomLoApple() {
        return new ConsLoApple(this.first.fall(), this.rest.randomLoApple());
    }

    // to determine whether an apple has fallen 
    // into a basket
    public boolean caughtApple(Basket b) {
        return this.first.caughtApple(this.first, b) ||
                this.rest.caughtApple(b);
    }

    // to draw a list of apples
    public WorldImage loAppleImage() {
        return new OverlayImages(this.first.appleImage(),
                this.rest.loAppleImage());
    }

}

// to represent the class basket
// x and y are positive integers representing its location
class Basket {
    int x;
    int y;

    int width = 80;
    int height = 30;


    // the constructor
    Basket(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // produces a new basket that moves when press a key
    Basket moveOnKey(String ke) {
        if (ke.equals("left")) {
            return new Basket(x - 10, y);
        }
        else if (ke.equals("right")) {
            return new Basket(x + 10, y);
        }
        else {
            return this;
        }
    }

    // to produce the basket image                  
    public WorldImage basketImage() {
        return new RectangleImage(new Posn(this.x, this.y - height / 2), 
                this.width, this.height, new Blue());
    }
}


// to represent the Apple Orchard Game
class AppleGame extends World {
    int appleCaught;
    ILoApple loa;
    Basket b;

    // the constructor
    AppleGame(int appleCaught, ILoApple loa, Basket b) {
        this.appleCaught = appleCaught;
        this.loa = loa;
        this.b = b;
    }


    // to produce a new AppleGame object with the 
    // apple fallen down
    public AppleGame onTick() {
        if (this.loa.caughtApple(this.b)) {
            return new AppleGame(this.appleCaught + 1, 
                    this.loa.randomLoApple(),
                    this.b);
        }
        else {
            return new AppleGame(this.appleCaught,
                    this.loa.randomLoApple(),
                    this.b);
        }
    }

    // produce the apple image                          
    public WorldImage appleTreeImage =
            new FromFileImage(new Posn(200, 200), "apple-tree.png");

    // to produce the World Image                    
    public WorldImage makeImage() {
        // draws the apple tree and the basket
        OverlayImages img1 = new OverlayImages(
                this.appleTreeImage, b.basketImage());
        return new OverlayImages(img1, 
                new OverlayImages(loa.loAppleImage(), 
                        appleCaughtDisplay()));
    }

    // to display the number of apple caught
    public WorldImage appleCaughtDisplay() {
        return new TextImage(new Posn(350, 50), 
                Integer.toString(this.appleCaught),
                50, new Black());
    }

    // to produce the game over image
    public WorldImage lastImage() {
        return new OverlayImages(this.makeImage(),
                new TextImage(new Posn(200, 200),
                        "You Won!", 50, new Red()));
    }

    // to produce a new instance of the class WorldEnd
    public WorldEnd worldEnds() {
        // if the appleCaught count to 20, the game ends
        if (this.appleCaught >= 20) {
            return new WorldEnd(true, this.lastImage()); 
        }
        else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    // to allow the player to control the basket 
    // on the ground
    public AppleGame onKeyEvent(String ke) {
        return new AppleGame(this.appleCaught, this.loa, 
                this.b.moveOnKey(ke));
    }

}


// examples of the game and tests for all methods
class ExamplesAppleGame {
    // produce a rectangle background
    WorldCanvas c = new WorldCanvas(400, 400);

    // represents an Apple
    Apple a1 = new Apple(100, 10, "red apple");
    Apple a2 = new Apple(100, 340, "red apple");
    Apple a3 = new Apple(100, 450, "small red apple");
    Apple a8 = new Apple(130, 340, "small red apple");

    Apple a4 = new Apple("red apple");

    Apple a5 = new Apple(100, 150, "small red apple");
    Apple a6 = new Apple(200, 50, "yellow apple");
    Apple a7 = new Apple(300, 80, "red apple");

    // represents a list of Apples
    ILoApple mtlist = new MtLoApple();
    ILoApple loa1 = new ConsLoApple(this.a1, this.mtlist);
    ILoApple loa2 = new ConsLoApple(this.a2, this.loa1);
    ILoApple loa3 = new ConsLoApple(this.a3, this.loa2);
    ILoApple loa4 = new ConsLoApple(this.a4, this.loa3);
    ILoApple loa5 = new ConsLoApple(this.a5,
            new ConsLoApple(this.a6, 
                    new ConsLoApple(this.a7,
                            this.mtlist)));
    ILoApple loa8 = new ConsLoApple(this.a8, this.mtlist);

    // represents a basket
    Basket b1 = new Basket(100, 400);
    Basket b2 = new Basket(200, 400);
    Basket b3 = new Basket(130, 400);

    // represents an Apple Game
    AppleGame game1 = new AppleGame(10, loa1, b1);
    AppleGame game2 = new AppleGame(15, loa2, b1);
    AppleGame game3 = new AppleGame(20, loa3, b1);
    AppleGame game4 = new AppleGame(21, loa4, b1);


    // display the falling apple
    boolean testMoveDown(Tester t) {
        return t.checkExpect(a1.moveDown(2), new Apple(100, 12, "red apple"));
    }

    // test the method moveOnKey
    boolean testMoveOnKey(Tester t) {
        return t.checkExpect(b1.moveOnKey("left"), new Basket(90, 400)) &&
                t.checkExpect(b1.moveOnKey("right"), new Basket(110, 400)) &&
                t.checkExpect(b1.moveOnKey("haha"), new Basket(100, 400));
    }

    // test the method onTheGround
    boolean testOnTheGround(Tester t) {
        return t.checkExpect(a1.onTheGround(), false) &&
                t.checkExpect(a2.onTheGround(), false) &&
                t.checkExpect(a3.onTheGround(), true);
    }


    // test the method fall                                        
    boolean testFall(Tester t) {
        Apple atest = new Apple(251, 400, "red apple");
        atest = atest.fall();

        return t.checkExpect(a1.fall(), 
                new Apple(100, 20, "red apple")) &&

                t.checkExpect(atest, 
                        new Apple(atest.x, 0, "red apple")); 
    }


    // test whether an apple is within range
    boolean testCheckRange(Tester t) {
        return t.checkRange(a4.x, 0, 400); 
    }

    // test the method caughtApple
    boolean testCaughtApple(Tester t) {
        return t.checkExpect(this.loa1.caughtApple(this.b1), false) &&
                t.checkExpect(this.loa2.caughtApple(this.b3), true) &&
                t.checkExpect(this.loa8.caughtApple(this.b3), true);
    }


    // test the method onKeyEvent               
    boolean testOnKeyEvent(Tester t) {
        return t.checkExpect(this.game1.onKeyEvent("left"), 
                new AppleGame(10, this.loa1, this.b1.moveOnKey("left"))) &&
                t.checkExpect(this.game2.onKeyEvent("right"), 
                        new AppleGame(15, this.loa2,
                                this.b1.moveOnKey("right")));
    }

    // test the method onTick
    boolean testOnTick(Tester t) {
        
        return t.checkExpect(this.game1.onTick(), 
                new AppleGame(10, 
                        new ConsLoApple(new Apple(100, 20, "red apple"),
                                this.mtlist), this.b1)) &&
                t.checkExpect(this.game2.onTick(),
                        new AppleGame(15, (new ConsLoApple(new Apple(a2.x, 0, 
                                "red apple"), this.loa1)), this.b1));
    }

    /* // test the method WorldEnds
    boolean testWorldEnds(Tester t) {
        return t.checkExpect(this.game1.worldEnds(), 
                new WorldEnd(false, new OverlayImages(b1.basketImage(), 
                        new OverlayImages(a1.appleImage(), 
                                new FromFileImage(new Posn(200, 200), 
                                        "apple-tree.png"))))) &&
                t.checkExpect(this.game3.worldEnds(), 
                        new WorldEnd(true, 
                                new OverlayImages(new TextImage(new Posn(200, 200),
                                        "Game Over", new Red()),
                                        new OverlayImages(b1.basketImage(), 
                                                new OverlayImages(a1.appleImage(), 
                                                        new FromFileImage(new Posn(200, 200), 
                                                                "apple-tree.png")))))) &&
                t.checkExpect(this.game4.worldEnds(), 
                        new WorldEnd(true, 
                                new OverlayImages(new TextImage(new Posn(200, 200),
                                        "Game Over", new Red()),
                                        new OverlayImages(b1.basketImage(), 
                                                new OverlayImages(a2.appleImage(), 
                                                        new FromFileImage(new Posn(200, 200), 
                                                                "apple-tree.png"))))));
    }
     */

    // test the method randomLoApple
    boolean testRandomLoApple(Tester t) {
        return t.checkExpect(this.loa1.randomLoApple(), 
                new ConsLoApple(new Apple(100, 20, "red apple"),
                        this.mtlist)) &&
                t.checkExpect(this.loa2.randomLoApple(),
                        new ConsLoApple(new Apple(this.a2.x, 370, "red apple"), 
                                new ConsLoApple(this.a1, this.mtlist)));
    }


    // run the animation
    AppleGame w1 = new AppleGame(0, loa1, b1);
    AppleGame w2 = new AppleGame(10, loa2, b2);
    AppleGame w3 = new AppleGame(20, loa3, b1);
    AppleGame w4 = new AppleGame(0, loa5, b1);


    // run the first game
    // boolean runAnimation = this.w4.bigBang(400, 400, 0.5);
}

