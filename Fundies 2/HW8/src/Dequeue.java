 import tester.Tester;

class Deque<T> {
    Sentinel<T> header;

    Deque() {
        this(new Sentinel<T>());
    }

    Deque(Sentinel<T> header) {
        this.header = header;
    }

    // counts the number of nodes in the deque
    public int size() {
        return this.header.next.size();
    }

    // insert that at the front of the list
    public void addAtHead(T t) {
        this.header.addAtHead(t);
    }

    // insert that at the tail of the list
    public void addAtTail(T t) {
        this.header.addAtTail(t);
    }

    // remove the first node from the head
    public T removeFromHead() {
        if (this.size() == 0) {
            throw new RuntimeException("no node to be removed");
        }
        return this.header.next.removeThis();
    }

    // remove the last node from the head
    public T removeFromTail() {
        if (this.size() == 0) {
            throw new RuntimeException("no node to be removed");
        }
        return this.header.prev.removeThis();
    }

    // find the first node pass the ipred
    public ANode<T> find(IPred<T> ipred) {
        return this.header.next.find(ipred);
    }

    // remove the given node from this deque
    public void removeNode(ANode<T> n) {
        n.removeThis();
    }
}

abstract class ANode<T> {
    ANode<T> next;
    ANode<T> prev;

    // counts the number of nodes in the anode
    public abstract int size();

    // find the first node pass the pred
    public abstract ANode<T> find(IPred<T> ipred);

    // remove this node
    public abstract T removeThis();
}

class Sentinel<T> extends ANode<T> {

    Sentinel() {
        this.next = this;
        this.prev = this;
    }

    // counts the number of nodes in the deque
    public int size() {
        return 0;
    }

    // insert that at the front of the list
    public void addAtHead(T t) {
        new Node<T>(t, this.next, this);
    }

    // insert that at the tail of the list
    public void addAtTail(T t) {
        new Node<T>(t, this, this.prev);
    }
    // remove nothing
    public T removeThis() {
        return null;
    }
    // find the first node pass the ipred
    public ANode<T> find(IPred<T> ipred) {
        return this;
    }
}
class Node<T> extends ANode<T> {
    T data;
    Node(T t) {
        this.data = t;
        this.prev = null;
        this.next = null;
    }
    Node(T t, ANode<T> next, ANode<T> prev) {
        this(t);
        this.prev = prev;
        this.next = next;
        if (prev == null || next == null) {
            throw new IllegalArgumentException("illegal argument for Node");
        }
        prev.next = this;
        next.prev = this;
    }
    // counts the number of nodes in the deque
    public int size() {
        return 1 + this.next.size();
    }
    // find the first node pass the ipred
    public ANode<T> find(IPred<T> ipred) {
        if (ipred.apply(this.data)) {
            return this;
        }
        else {
            return this.next.find(ipred);
        }
    }
    public T removeThis() {
        this.next.prev = this.prev;
        this.prev.next = this.next;
        return this.data;
    }
}
// Represents a boolean-valued question over values of type T
interface IPred<T> {
    boolean apply(T t);
}
// return true if the string's length is bigger than 3
class SizeGreaterThan3 implements IPred<String> {
    public boolean apply(String s) {
        return s.length() > 3;
    }
}
class Isabc implements IPred<String> {
    public boolean apply(String s) {
        return s.equals("abc");
    }
}


class ExamplesDeque {

    Deque<String> deque1;
    Deque<String> deque2;
    Deque<String> deque3;

    Sentinel<String> sent2;
    Node<String> n21;
    Node<String> n22;
    Node<String> n23;
    Node<String> n24;

    Sentinel<String> sent3;
    Node<String> n31;
    Node<String> n32;
    Node<String> n33;
    Node<String> n34;

    void initialize() {
        sent2 = new Sentinel<String>();
        n21 = new Node<String>("abc", sent2, sent2);
        n22 = new Node<String>("bcd", sent2, n21);
        n23 = new Node<String>("cde", sent2, n22);
        n24 = new Node<String>("def", sent2, n23);
        

        sent3 = new Sentinel<String>();
        n31 = new Node<String>("faw", sent3, sent3);
        n32 = new Node<String>("alr", n31, sent3);
        n33 = new Node<String>("jfeaa", n32, sent3);
        n34 = new Node<String>("kale", n33, sent3);

        deque1 = new Deque<String>();
        deque2 = new Deque<String>(sent2);
        deque3 = new Deque<String>(sent3);

    }

    // tests for the methods size()
    void testSize(Tester t) {
        this.initialize();
        t.checkExpect(this.deque1.size(), 0);
        t.checkExpect(this.deque2.size(), 4);
        t.checkExpect(this.deque3.size(), 4);
    }

//    void testAddHead(Tester t) {
//        this.initialize();
//        this.deque1.addAtHead("def");
//        this.deque1.addAtHead("cde");
//        this.deque1.addAtHead("bcd");
//        this.deque1.addAtHead("abc");
//        t.checkExpect(this.deque1, this.deque2);
//
//        this.initialize();
//        this.deque1.addAtHead("kale");
//        this.deque1.addAtHead("jfeaa");
//        this.deque1.addAtHead("alr");
//        this.deque1.addAtHead("faw");
//        t.checkExpect(this.deque1, this.deque3);
//    }

//    void testAddTail(Tester t) {
//        this.initialize();
//        this.deque1.addAtTail("abc");
//        this.deque1.addAtTail("bcd");
//        this.deque1.addAtTail("cde");
//        this.deque1.addAtTail("def");
//        t.checkExpect(this.deque1, this.deque2);
//
//        this.initialize();
//        this.deque1.addAtTail("faw");
//        this.deque1.addAtTail("alr");
//        this.deque1.addAtTail("jfeaa");
//        this.deque1.addAtTail("kale");
//        t.checkExpect(this.deque1, this.deque3);
//    }

    void testRemoveHead(Tester t) {
        this.initialize();

        t.checkException(new RuntimeException("no node to be removed"),
                this.deque1, "removeFromHead");

        this.deque1.addAtHead("def");
        this.deque1.addAtHead("cde");
        this.deque1.addAtHead("bcd");
        this.deque2.removeFromHead();
        t.checkExpect(this.deque2, this.deque1);

    }

    void testRemoveTail(Tester t) {
        this.initialize();

        t.checkException(new RuntimeException("no node to be removed"),
                this.deque1, "removeFromTail");

        this.deque1.addAtTail("abc");
        this.deque1.addAtTail("bcd");
        this.deque1.addAtTail("cde");
        this.deque2.removeFromTail();
        t.checkExpect(this.deque1, this.deque2);

    }

//    void testFind(Tester t) {
//        this.initialize();
//
//        IPred<String> i1 = new SizeGreaterThan3();
//
//        t.checkExpect(this.deque1.find(i1), this.deque1.header);
//        t.checkExpect(this.deque2.find(i1), this.deque2.header);
//        t.checkExpect(this.deque3.find(i1), this.deque3.header.next.next.next);
//
//        IPred<String> i2 = new Isabc();
//
//        t.checkExpect(this.deque1.find(i2), this.deque1.header);
//        t.checkExpect(this.deque2.find(i2), this.deque2.header.next);
//    }

    void testRemoveThis(Tester t) {
        this.initialize();

        sent2.removeThis();
        n21.removeThis();

        t.checkExpect(sent2, n24.next);
        t.checkExpect(sent2.next, n22);
        t.checkExpect(n22.prev, sent2);

    }

    // NOTE: Supposed to fail
    ANode<String> countructNullNode() {
        return new Node<String>("", new Node<String>(""), null);
    }

//    // to test the constructor error in Node<T>
//    void testConstructionNUllNodes(Tester t) {
//        t.checkException(new IllegalArgumentException(
//                "illegal argument for Node"), this, "constructNullNode");
//    }

    void testRemoveNode(Tester t) {
        this.initialize();

        this.deque2.removeNode(this.deque2.header);
        t.checkExpect(sent2, this.deque2.header);

        this.deque2.removeNode(n21);
        t.checkExpect(sent2.next, n22);
        t.checkExpect(n22.prev, sent2);

    }
}
