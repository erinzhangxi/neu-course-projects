import tester.*;

//Assignment 7

//Zhang Xi

//xizhang2

//Yifan Xing

//xingyif

// represent a list of T
interface IList<T> {
    // to represent the results of applying the given
    // function to this list
    <R> R accept(IListVisitor<T, R> v); 

    // represent a map method 
    <R> IList<R> map(IFunc<T, R> f);

    // represent a filter method
    IList<T> filter(IPred<T> pred);

    // append the given list to this list
    IList<T> append(IList<T> that);
}

// represent an empty list class
class Empty<T> implements IList<T> {

    // append the given list to this list
    public IList<T> append(IList<T> that) {
        return that;
    }

    // to represent the results of applying the given
    // function to this list
    public <R> R accept(IListVisitor<T, R> v) {
        return v.visitEmpty(this);
    }

    // represent a map method
    public <R> IList<R> map(IFunc<T, R> f) {       
        return new Empty<R>();
    }

    // represent a filter method
    public IList<T> filter(IPred<T> pred) {
        return this;
    }
}

// represent a cons of T class
class Cons<T> implements IList<T> {
    T first;
    IList<T> rest;
    Cons(T first, IList<T> rest) {
        this.first = first;
        this.rest = rest;
    }

    // append a given list to that list
    public IList<T> append(IList<T> that) {
        return new Cons<T>(this.first, this.rest.append(that));
    }

    // to represent the results of applying the given
    // function to this list
    public <R> R accept(IListVisitor<T, R> v) {
        return v.visitCons(this);
    }

    // accept the map method
    public <R> IList<R> map(IFunc<T, R> f) {
        return new Cons<R>(f.apply(this.first), this.rest.map(f));
    }

    // represent a filter method
    public IList<T> filter(IPred<T> pred) {
        if (pred.apply(this.first)) {
            return new Cons<T>(this.first, this.rest.filter(pred));
        }
        else {
            return this.rest.filter(pred);
        }
    }
}

/////////////////////////////////////////////////////////
// Visitor

//represent a visitor interface
interface IListVisitor<T, R> {
    R visitEmpty(Empty<T> e);
    R visitCons(Cons<T> c);
}

//represent a map visitor
class MapVisitor<T, R> implements IListVisitor<T, IList<R>> {
    IFunc<T, R> func;

    // the constructor
    MapVisitor(IFunc<T, R> func) {
        this.func = func;
    }

    // to visit empty class
    public IList<R> visitEmpty(Empty<T> e) {
        return new Empty<R>();
    }

    // to visit cons class
    public IList<R> visitCons(Cons<T> c) {
        return new Cons<R>(func.apply(c.first), c.rest.accept(this));
    }
}

// represent a filter visitor
class FilterVisitor<T> implements IListVisitor<T, IList<T>> {
    IPred<T> pred;

    // the constructor
    FilterVisitor(IPred<T> pred) {
        this.pred = pred;
    }

    // to visit empty class
    public IList<T> visitEmpty(Empty<T> e) {
        return new Empty<T>();
    }

    // to visit cons class
    public IList<T> visitCons(Cons<T> c) {
        if (pred.apply(c.first)) {
            return new Cons<T>(c.first, c.rest.accept(this));
        }
        else {
            return c.rest.accept(this);
        }
    }
}

/////////////////////////////////////////////////////////////////
// Apply

//represent a IFunction class
interface IFunc<X, R> {
    R apply(X x);
}

//represent a IFunction 2 class
interface IFunc2<A, B, R> {
    R apply(A a, B b);
}

// represent a predicate
interface IPred<T> {
    boolean apply(T t);
}

// represent a method that takes a list of books
// and return a list of their titles
class BookTitle implements IFunc<Book, String> {
    public String apply(Book b) {
        return b.title;
    }
}

// determine if a book's title has more than 5 letters
class TitleMoreThan5 implements IPred<Book> {
    public boolean apply(Book b) {
        return b.title.length() > 5;
    }
}

// return the area of a shape
class CircleRadius implements IFunc<Circle, Double> {
    public Double apply(Circle c) {
        return c.radius * 1.0;
    }
}

// determine if the circle has a radius bigger than 10
class RadiusBiggerThan10 implements IPred<Circle> {
    public boolean apply(Circle c) {
        return c.radius > 10;
    }
}

//////////////////////////////////////////////////////////
// Book
// represent a book class
class Book {
    String title;
    String author;
    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
/////////////////////////////////////////////////////////////

//to represent a circle
class Circle {
    int x; // represents the center of the circle
    int y;
    int radius;
    String color;

    // the constructor
    Circle(int x, int y, int radius, String color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }
}

//////////////////////////////////////////////////////////////
// represent an example class
class ExamplesVisitor {
    
    // examples of strings
    IList<String> mtstring = new Empty<String>();
    IList<String> strings1 = new Cons<String>("apple", 
            new Cons<String>("banana", 
                    new Cons<String>("candy",
                            new Cons<String>("pineapple",
                                    this.mtstring))));
    IList<String> strings2 = new Cons<String>("cookies", 
            new Cons<String>("mango", this.mtstring));
    IList<String> strings3 = new Cons<String>("apple", 
            new Cons<String>("banana", 
                    new Cons<String>("candy",
                            new Cons<String>("pineapple", 
                                    new Cons<String>("cookies", 
                                            new Cons<String>("mango",
                                                    this.mtstring))))));

    // examples of books
    Book b1 = new Book("a", "author1");
    Book b2 = new Book("b", "author2");
    Book b3 = new Book("cccccc", "author3");
    Book b4 = new Book("dddddd", "author4");

    IList<Book> mtbook = new Empty<Book>();
    IList<Book> books1 = new Cons<Book>(this.b1, 
            new Cons<Book>(this.b2, 
                    new Cons<Book>(this.b3, this.mtbook)));
    IList<Book> books2 = new Cons<Book>(this.b4, 
            new Cons<Book>(this.b3, 
                    new Cons<Book>(this.b2, this.mtbook)));
    
    // examples of circles
    Circle c1 = new Circle(50, 50, 10, "red");
    Circle c2 = new Circle(90, 50, 50, "blue");
    IList<Circle> mtcircle = new Empty<Circle>();
    IList<Circle> circles1 = new Cons<Circle>(this.c1, this.mtcircle);
    IList<Circle> circles2 = new Cons<Circle>(this.c2, this.circles1);

    IFunc<Book, String> bookTitle = new BookTitle();
    MapVisitor<Book, String> mapBookTitleVisitor = 
            new MapVisitor<Book, String>(bookTitle);

    IPred<Book> moreThan5 = new TitleMoreThan5();
    FilterVisitor<Book> filterBookTitleVisitor = 
            new FilterVisitor<Book>(moreThan5);
    
    IFunc<Circle, Double> circleRadius = new CircleRadius();
    MapVisitor<Circle, Double> mapCircleDoubleVisitor = 
            new MapVisitor<Circle, Double>(circleRadius);
    
    IPred<Circle> BiggerThan10 = new RadiusBiggerThan10();
    FilterVisitor<Circle> filterCircleRadiusVisitor = 
            new FilterVisitor<Circle>(BiggerThan10);


    // test the method append()
    boolean testAppend(Tester t) {
        return t.checkExpect(this.mtstring.append(this.strings1),
                this.strings1) &&
                t.checkExpect(this.strings1.append(this.strings2),
                        this.strings3);
    }


    // test the method map()
    boolean testMap(Tester t) {
        return t.checkExpect(this.mtbook.map(bookTitle), this.mtstring) &&
                t.checkExpect(this.books1.map(bookTitle), 
                        new Cons<String>("a",
                                new Cons<String>("b",
                                        new Cons<String>("cccccc",
                                                this.mtstring)))) &&
                t.checkExpect(this.mtbook.filter(moreThan5), this.mtbook) &&
                t.checkExpect(this.books2.filter(moreThan5), 
                        new Cons<Book>(this.b4, 
                                new Cons<Book>(this.b3,
                                        this.mtbook)));
    }
    
    // test the method IListVisitor()
    boolean testVisitor(Tester t) {
        return t.checkExpect(this.mtbook.accept(mapBookTitleVisitor), 
                new Empty<String>()) &&
                t.checkExpect(this.books1.accept(mapBookTitleVisitor),
                        new Cons<String>("a", 
                                new Cons<String>("b", 
                                        new Cons<String>("cccccc",
                                                this.mtstring)))) &&
                t.checkExpect(this.mtcircle.accept(mapCircleDoubleVisitor), 
                        new Empty<Double>()) &&
                t.checkExpect(this.circles1.accept(mapCircleDoubleVisitor), 
                        new Cons<Double>(10.0, new Empty<Double>())) &&
                t.checkExpect(this.circles2.accept(mapCircleDoubleVisitor), 
                        new Cons<Double>(50.0, 
                                new Cons<Double>(10.0,
                                        new Empty<Double>()))) &&
                t.checkExpect(this.mtbook.accept(filterBookTitleVisitor),
                        mtstring) &&
                t.checkExpect(this.books1.accept(filterBookTitleVisitor),
                        new Cons<Book>(this.b3, 
                                this.mtbook)) &&
                t.checkExpect(this.mtcircle.accept(filterCircleRadiusVisitor),
                        mtcircle) &&
                t.checkExpect(this.circles2.accept(filterCircleRadiusVisitor),
                        new Cons<Circle>(this.c2, this.mtcircle)) &&
                t.checkExpect(this.circles1.accept(filterCircleRadiusVisitor),
                        this.mtcircle);
    }

}
