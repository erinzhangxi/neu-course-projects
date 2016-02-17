import tester.Tester;

// Practice 5
// Problem 10.6 on page 102

public class PImage {
    int width; 
    int height;
    String source;
    
        PImage(int width, int height, String source) {
            this.width = width;
            this.height = height;
            this.source = source;
        }

/* TEMPLEATE
 * FIELDS:
 * ... this.width ...  -- int
 * ... this.height ... -- int
 * ... this.source ... -- String
 * 
 * METHODS:
 * ... this.sizeString() ...  -- String
 * ... this.area()...   -- int
 */

// is this image large?
String sizeString() {
    if (this.area() < 10000) {
        return "small";}
    else { 
        if(10001 < this.area() && this.area() <1000000) {
            return "medium";}
        else {
            return "large";}
        }
    }

// determine the pixel area of this image
int area() {
    return this.width * this.height;
    }

}

class ExamplesPImages {
	PImage i1 = new PImage(20, 50, "Google");
	PImage i2 = new PImage(100, 1000, "Yahoo");
	PImage i3 = new PImage(30, 190000000, "...");
	
    boolean testSizeString(Tester t) {
    	return t.checkExpect(this.i1.sizeString(),"small") &&
    			t.checkExpect(this.i2.sizeString(), "medium") &&
    			t.checkExpect(this.i3.sizeString(), "large");
    }
}

/*
 * How to do check expect ?
    class ExamplesPImages {
   
    check new PImage1(20, 50).sizeStringt() expect "small";
    check new PImage2(300, 50).sizeString() expect "medium";
    check new PImage3(5000, 5000).sizeString() expect "large";

}
 */
