import tester.Tester;

// Assignment 6
// Zhang Xi
// xizhang2
// Yifan Xing
// xingyif



// TODO
// add compare strings in comparator
// == 0


// to represent a list of T
interface IList<T> {

    //determine whether a list of T is sorted
    Boolean isSorted(IComparator<T> comp);

    // compares the first T in the list with the second string in the list
    boolean pullSecond(T t, IComparator<T> comp);

    // to determine if this list of T is empty
    boolean isEmpty();

    // to merge this sorted list with the given sorted list
    IList<T> merge(IList<T> that, IComparator<T> comp);

    // to compare two ConsList of T's
    Cons<T> mergeHelp(Cons<T> ts, IComparator<T> comp);

    // to produce a sorted list given by the IComparator<String> method
    IList<T> sort(IComparator<T> comp);

    // to insert a T into a sorted list
    IList<T> insert(T t, IComparator<T> comp);


    // to determine if this list of strings and the given list of strings
    // are the same data in the same order
    boolean sameList(IList<T> that);

    // helper function for sameList method
    boolean sameListHelp(Cons<T> that);


}

//to represent an empty list of T
class Empty<T> implements IList<T> {

    //determine whether a list of strings is sorted
    public Boolean isSorted(IComparator<T> comp) {
        return true;
    }

    // compares the first string in the 
    // list with the second string in the list
    public boolean pullSecond(T t, IComparator<T> comp) {
        return true;
    }

    // to determine if this list of T is empty
    public boolean isEmpty() { 
        return true;
    }

    // to merge this sorted list with the given sorted list
    public IList<T> merge(IList<T> that, IComparator<T> comp) {
        return that;
    }

    // to compare two ConsList of T's
    public Cons<T> mergeHelp(Cons<T> ts, IComparator<T> comp) {
        throw new RuntimeException(
                "No Cons<String> of runners available at this time!");
    }

    // to produce a sorted list given by the IComparator<String> method
    public IList<T> sort(IComparator<T> comp) {
        return this;
    }

    // to insert a string into a sorted list
    public IList<T> insert(T t, IComparator<T> comp) {
        return new Cons<T>(t, this);
    }


    // to determine if this list of elements and the given list of elements
    // are the same data in the same order
    public boolean sameList(IList<T> that) {
        return that.isEmpty();   
    }

    // helper function for sameList method
    public boolean sameListHelp(Cons<T> that) {
        return false;
    }

}

// to represent a cons list of T
class Cons<T> implements IList<T> {
    T first;
    IList<T> rest;

    Cons(T first, IList<T> rest) {
        this.first = first;
        this.rest = rest;
    }  

    // determine whether a list of string is sorted
    public Boolean isSorted(IComparator<T> comp) {
        return this.rest.pullSecond(this.first, comp) &&
                this.rest.isSorted(comp);
    }

    // compares the first string in the list
    // with the second string in the list
    public boolean pullSecond(T t, IComparator<T> comp) {
        return comp.compare(t, this.first) < 0;
    }

    // to determine if this list of T is empty
    public boolean isEmpty() {
        return false;
    }

    // to merge this sorted list with the given sorted list
    public IList<T> merge(IList<T> that, IComparator<T> comp) {
        if (that.isEmpty()) {
            return this;
        }
        else {
            return that.mergeHelp(this, comp);
        }
    }

    // to compare two ConsList of T's
    public Cons<T> mergeHelp(Cons<T> ts, IComparator<T> comp) {
        if (comp.compare(this.first, ts.first) < 0) {
            return new Cons<T>(this.first, 
                    this.rest.merge(ts, comp));
        }
        else {
            return new Cons<T>(ts.first, this.merge(ts.rest, comp));
        }
    }

    // to produce a sorted list given by the IComparator<String> method
    public IList<T> sort(IComparator<T> comp) {
        return this.rest.sort(comp).insert(this.first, comp);
    }

    // to insert a string into a sorted list
    public IList<T> insert(T t, IComparator<T> comp) {
        if (comp.compare(t, this.first) < 0) {
            return new Cons<T>(t, this.rest.insert(this.first, comp));
        }
        else {
            return new Cons<T>(this.first, this.rest.insert(t, comp));
        }
    }

    // to determine if this list of T's and the given list of T's
    // are the same data in the same order
    public boolean sameList(IList<T> that) {
        if (that.isEmpty()) {
            return false;
        }
        else {
            return this.sameListHelp((Cons<T>)that); 
        }
    }

    // helper function for sameList method
    public boolean sameListHelp(Cons<T> that) {
        return this.first.equals(that.first) && this.rest.sameList(that.rest);

    }
}

interface IFun<X, Y> {
    //applies an operation to X and produces a Y
    Y apply(X x);
}

//Interface for two-argument function-objects with signature [A1, A2 -> R]
interface IFun2<X, Y, R> {

    R apply(X x, Y y);
}

interface IComparator<T> {
    // Returns a negative number if t1 comes before t2 in this ordering
    // Returns zero              if t1 is the same as t2 in this ordering
    // Returns a positive number if t1 comes after t2 in this ordering
    int compare(T t1, T t2);
}

//to compare the Strings lexicographically
class StringLexCompGen implements IComparator<String> {

    // to compare which string comes before
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}

class StringLengthCompGen implements IComparator<String> {
    // to compare the strings by their lengths from the shortest
    // to the longest
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}




class Examples {

    String s1 = "Happy";
    String s2 = "AH";
    String s3 = "Banana";
    String s4 = "Cup";

    IList<String> mt = new Empty<String>();
    IList<String> unsorted1 = new Cons<String>(this.s1, 
            new Cons<String>(this.s2, 
                    new Cons<String>(this.s3, this.mt)));
    IList<String> unsorted2 = new Cons<String>(this.s3, 
            new Cons<String>(this.s2, 
                    new Cons<String>(this.s1, this.mt)));
    IList<String> lexSorted1 = new Cons<String>(this.s2, 
            new Cons<String>(this.s3, 
                    new Cons<String>(this.s4,
                            new Cons<String>(this.s1, this.mt))));
    IList<String> lengthSorted1 = new Cons<String>(this.s2, 
            new Cons<String>(this.s4,
                    new Cons<String>(this.s1,
                            new Cons<String>(this.s3, this.mt))));
    IList<String> lexSorted2 = new Cons<String>(this.s2,
            new Cons<String>(this.s1, this.mt));
    IList<String> lengthSorted2 = new Cons<String>(this.s4, 
            new Cons<String>(this.s3, this.mt));

    IList<String> unsorted3 = new Cons<String>(this.s1, 
            new Cons<String>(this.s2, 
                    new Cons<String>(this.s3,
                            new Cons<String>(this.s4, this.mt))));
    IList<String> unsorted4 = new Cons<String>(this.s3, 
            new Cons<String>(this.s4, this.mt));

    Cons<String> consLex1 = new Cons<String>(this.s2,
            new Cons<String>(this.s3, 
                    new Cons<String>(this.s4,
                            new Cons<String>(this.s1, this.mt))));
    Cons<String> consLex2 = new Cons<String>(this.s2, 
            new Cons<String>(this.s1, this.mt));
    Cons<String> consLength1 = new Cons<String>(this.s2, 
            new Cons<String>(this.s4,
                    new Cons<String>(this.s1,
                            new Cons<String>(this.s3, this.mt))));
    Cons<String> consLength2 = new Cons<String>(this.s4, 
            new Cons<String>(this.s3, this.mt));

    IComparator<String> stringLex = new StringLexCompGen();
    IComparator<String> stringLength = new StringLengthCompGen();
    
    
    // to test the method isSorted                              
    boolean testIsSorted(Tester t) {
        return t.checkExpect(this.mt.isSorted(new StringLexCompGen()),
                true) &&
                t.checkExpect(this.mt.isSorted(new StringLengthCompGen()),
                        true) &&
                t.checkExpect(this.unsorted1.isSorted(new StringLexCompGen()),
                        false) &&
                t.checkExpect(
                        this.unsorted2.isSorted(new StringLengthCompGen()),
                        false) &&
                t.checkExpect(this.lexSorted1.isSorted(new StringLexCompGen()),
                        true) &&
                t.checkExpect(
                        this.lengthSorted1.isSorted(new StringLengthCompGen()),
                        true);
    }

    // to test the method isEmpty
    boolean testIsEmpty(Tester t) {
        return t.checkExpect(this.mt.isEmpty(), true) &&
                t.checkExpect(this.unsorted1.isEmpty(), false) &&
                t.checkExpect(this.lexSorted1.isEmpty(), false);
    }


    IList<String> sortedlist = new Cons<String>(this.s2,
            new Cons<String>(this.s2,  new Cons<String>(this.s3,
                    new Cons<String>(this.s4, new Cons<String>(this.s1,
                            new Cons<String>(this.s1, this.mt))))));

    IList<String> sortedlist2 = new Cons<String>(this.s2,
            new Cons<String>(this.s4, new Cons<String>(this.s4,
                    new Cons<String>(this.s1, new Cons<String>(this.s3,
                            new Cons<String>(this.s3, this.mt))))));

    // to test the method merge
    boolean testMerge(Tester t) {
        return t.checkExpect(this.mt.merge(this.mt, new StringLexCompGen()),
                this.mt) &&
                t.checkExpect(this.mt.merge(this.lexSorted1,
                        new StringLexCompGen()), this.lexSorted1) &&
                t.checkExpect(this.mt.merge(this.lengthSorted1,
                        new StringLengthCompGen()), this.lengthSorted1) &&
                t.checkExpect(this.lexSorted2.merge(this.mt,
                        new StringLexCompGen()), this.lexSorted2) &&
                t.checkExpect(this.lexSorted1.merge(this.lexSorted2,
                        new StringLexCompGen()), sortedlist) &&
                t.checkExpect(this.lengthSorted1.merge(this.lengthSorted2,
                        new StringLengthCompGen()), 
                        sortedlist2);
    }

    // to test the method mergeHelp()
    boolean testMergeHelp(Tester t) {
        return //TODO 
                // t.checkException(this.mt.mergeHelp(this.lexSorted1, 
                // new StringLexComp()), 
                // "No Cons<String> of runners available at this time!") &&
                t.checkExpect(this.consLex2.mergeHelp(this.consLex1, this.stringLex), 
                        new Cons<String>(this.s2, new Cons<String>(this.s2,
                                new Cons<String>(this.s3, new Cons<String>(this.s4,
                                        new Cons<String>(this.s1, new Cons<String>(this.s1,
                                                this.mt))))))) &&
                t.checkExpect(this.consLength1.mergeHelp(this.consLength2, this.stringLength), 
                        new Cons<String>(this.s2, new Cons<String>(this.s4,
                                new Cons<String>(this.s4,
                                        new Cons<String>(this.s1, 
                                                new Cons<String>(this.s3, 
                                                        new Cons<String>(this.s3, 
                                                                this.mt)))))));
    }

    // to test the sort method
    boolean testSort(Tester t) {
        return t.checkExpect(this.mt.sort(this.stringLex), this.mt) &&
                t.checkExpect(this.mt.sort(this.stringLength), this.mt) &&
                t.checkExpect(this.unsorted3.sort(this.stringLex), this.lexSorted1) &&
                t.checkExpect(this.unsorted4.sort(this.stringLength), this.lengthSorted2);
    }

    // to test the insert method
    boolean testInsert(Tester t) {
        return t.checkExpect(this.mt.insert(this.s1, this.stringLex),
                new Cons<String>(this.s1, this.mt)) &&
                t.checkExpect(this.mt.insert(this.s2, this.stringLength),
                        new Cons<String>(this.s2, this.mt)) &&
                t.checkExpect(this.lexSorted1.insert(this.s1, this.stringLex),
                        new Cons<String>(this.s2, new Cons<String>(this.s3, 
                                new Cons<String>(this.s4, new Cons<String>(this.s1, 
                                        new Cons<String>(this.s1, this.mt)))))) &&
                t.checkExpect(this.lengthSorted1.insert(this.s2, this.stringLength),
                        new Cons<String>(this.s2, new Cons<String>(this.s2, 
                                new Cons<String>(this.s4, new Cons<String>(this.s1, 
                                        new Cons<String>(this.s3, this.mt))))));
    }

    // to test the sameList method
    boolean testSameList(Tester t) {
        return t.checkExpect(this.mt.sameList(this.mt), true) &&
                t.checkExpect(this.mt.sameList(this.lexSorted1), false) &&
                t.checkExpect(this.lexSorted1.sameList(this.mt), false) &&
                t.checkExpect(this.lexSorted1.sameList(this.lexSorted1), true) &&
                t.checkExpect(this.lengthSorted1.sameList(this.lengthSorted1), true) &&
                t.checkExpect(this.lexSorted1.sameList(this.lengthSorted2), false);
    }

    // to test the sameListHelp()
    boolean testSameListHelp(Tester t) {
        return t.checkExpect(this.mt.sameListHelp(this.consLex1), false) &&
                t.checkExpect(this.consLex1.sameListHelp(this.consLex1), true) &&
                t.checkExpect(this.consLex2.sameListHelp(this.consLength1), false) &&
                t.checkExpect(this.consLex1.sameListHelp(this.consLex1), true);
    }
    
}
