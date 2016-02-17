// Problem 12.1 on page 125

// the class of all geometric shapes
interface IShape {
    // to compute the area of this shape
    double area();
    
    // to compute the distance of this shape to the origin
    double distTo0();
    
    // is the given point within the bounds of this shape
    boolean in(CartPt p);
    
    // compute the bounding box for this shape
    Square bb();

}

// a Cartesian point in the plane
class CartPt {
    int x;
    int y;
    
    CartPt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    double distTo0() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    
    boolean same(CartPt p) {
        return (this.x == p.x) && (this.y == p.y);
   
    }
    
    // the distance between this point to that point
    double distanceTo(CartPt that) {
        return Math.sqrt((that.x - this.x) * (that.x - this.x) 
                -
                (that.y - this.y) * (that.y - this.y));
    }
    
    // create a point that is delta pixels(up, left) from this
    CartPt translate(int delta) {
        return new CartPt(this.x - delta, this.y - delta);
    }
      
    
}

// a dot
class Dot implements IShape {
    CartPt loc;
    
    Dot(CartPt loc) {
        this.loc = loc;
    }
     
    // to compute the area of this Dot
    public double area() {
        return 0;
    }
    
    // to compute the distance of this Dot to the origin
    public double distTo0() {
        return this.loc.distTo0();
    }
    
    // is the given point within the bounds of this Dot?
    public boolean in(CartPt p) {
        return this.loc.same(p);
    }
    
    // compute the bounding box for this Dot
    public Square bb() {
        return new Square(this.loc, 1);
    }
    
}

// a square
class Square implements IShape {
    CartPt loc;
    int size;
    
    Square(CartPt loc, int size) {
        this.loc = loc;
        this.size = size;
    }
    
    // to compute the area of this Square
    public double area() {
        return this.size * this.size;
    }
    
    // to compute the distance of this Square to the origin
    public double distTo0() {
        return this.loc.distTo0();
    }
    
    // is the given point within the bounds of this Square?
    public boolean in(CartPt p) {
        return this.between(this.loc.x, p.x, this.size)
                && this.between(this.loc.y, p.y, this.size);
    }
    
    // is x in the interval[lft, lft+wdth]?
    public boolean between(int lft, int x, int wdth) {
        return lft <= x && x <= lft + wdth;
    }
    
    // compute the bounding box for this Square
    public Square bb() {
        return this;
    }
}

// a circle 
class Circle implements IShape {
    CartPt loc;
    int radius;
    
    Circle(CartPt loc, int radius) {
        this.loc = loc;
        this.radius = radius;
    }
    
    // to compute the area of this Circle
    public double area() {
        return this.radius * this.radius;
    }
    
    // to compute the distance of this Circle to the origin
    public double distTo0() {
        return this.loc.distTo0() - this.radius;
    }
    
    // is the given point within the bounds of this Circle?
    public boolean in(CartPt p) {
        return this.loc.distanceTo(p) <= this.radius;
    }
    
    // compute the bounding box for this Circle
    public Square bb() {
        return 
                new Square(this.loc.translate(this.radius), 2 * this.radius);
    }
}