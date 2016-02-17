import tester.*;

// Assignment 6
//Zhang Xi
//xizhang2
//Yifan Xing
//xingyif


interface IComparator<T> {

    // represent a method compare two T
    boolean inOrder(T t1, T t2);
}

//to represent a method class BooksByTitle
class BooksByTitle implements IComparator<Book> {
    // represent a method compare two Books
    public boolean inOrder(Book b1, Book b2) {
        return b1.title.compareTo(b2.title) < 0;
    }
}

//to represent a method class BooksByAuthor
class BooksByAuthor implements IComparator<Book>{
    public boolean inOrder(Book b1, Book b2) {
        return b1.author.compareTo(b2.author) < 0;
    }
}

//to represent a method class BooksByPrice
class BooksByPrice implements IComparator<Book> {
    public boolean inOrder(Book b1, Book b2) {
        return b1.price - b2.price < 0;
    }
}



//to represent an abstract binary search tree
abstract class ABST<T> {
    IComparator<T> order;
    // the constructor
    ABST(IComparator<T> order) {
        this.order = order;
    }

    // take in an item and produce a new binary tree given the order
    abstract ABST<T> insert(T t);
}


// to represent a leaf class
class Leaf<T> extends ABST<T> {
    // the constructor
    Leaf(IComparator<T> order) {
        super(order);
    }

    // take in an item and produce a new binary tree given the order
    public ABST<T> insert(T t) {
        return new Node<T>(t, new Leaf<T>(order), new Leaf<T>(order), order);
    }
}

// to represent a node class
// data from the left tree come first,
// then the current data, and the data from the right
// side come the last
class Node<T> extends ABST<T> {
    T data;
    ABST<T> left;
    ABST<T> right;

    // the constructor
    Node(T data, ABST<T> left, ABST<T> right, IComparator<T> order) {
        super(order);
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // take in an item and produce a new binary tree given the order
    public ABST<T> insert(T t) {
        if (order.inOrder(t, data)) {
            return new Node<T>(t, left.insert(data), right, order);
        }
        else {
            return new Node<T>(t, left, right.insert(data), order);
        }
    }


// to represent a book class
class Book {
    String title;
    String author;
    int price;

    // the constructor
    Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}




class ExamplesABST {
    Book b1 = new Book("HtDP", "zhang", 10);
    Book b2 = new Book("The Little Prince", "Antoine", 20);
    Book b3 = new Book("Red Hat", "J.D.", 50);
    
    IComparator<Book> booksByTitle = new BooksByTitle();
    IComparator<Book> booksByAuthor = new BooksByAuthor();
    IComparator<Book> boosByPrice = new BooksByPrice();
    

    ABST<Book> mtBook = new Leaf<Book>();
    ABST<Book> books1 = new Node<Book>(this.b1, this.mtBook, this.mtBook);
    ABST<Book> books2 = new Node<Book>(this.b2, this.books1, this.mtBook);
    ABST<Book> books3 = new Node<Book>(this.b3, this.books1, this.books2);

    boolean testInOrder(Tester t) {
        return t.checkExpect(new BooksByTitle().inOrder(this.b1, this.b2), true) &&
                t.checkExpect(new BooksByAuthor().inOrder(this.b2, this.b3), true) &&
                t.checkExpect(new BooksByPrice().inOrder(this.b3, this.b2), false);

    }

}