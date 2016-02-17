import tester.*;

// Assignment 6

//Zhang Xi

//xizhang2

//Yifan Xing

//xingyif


// to represent a list of Strings
interface ILoString {

    // determine whether a list of strings is sorted
    boolean isSorted(IStringsCompare comp);

    // compares the first string in the list with the second string in the list
    boolean pullSecond(String s, IStringsCompare comp);

    // to determine if this list of string is empty
    boolean isEmpty();

    // to merge this sorted list of String with the given sorted list
    // of String
    ILoString merge(ILoString that, IStringsCompare comp);

    // to compare two ConsLoStrings
    ConsLoString mergeHelp(ConsLoString los, IStringsCompare comp);

    // to produce a sorted list given by the IStringsCompare method
    ILoString sort(IStringsCompare comp);

    // to insert a string into a sorted list
    ILoString insert(String s, IStringsCompare comp);

    // to determine if this list of strings and the given list of strings
    // are the same data in the same order
    boolean sameList(ILoString that);

    // helper function for sameList method
    boolean sameListHelp(ConsLoString that);

}

//to represent an empty list of Strings
class MtLoString implements ILoString {

    // determine whether a list of string is sorted
    public boolean isSorted(IStringsCompare comp) {
        return true;
    }

    // compares the first string in the list with the second string in the list
    public boolean pullSecond(String s, IStringsCompare comp) {
        return true;
    }

    // to determine if this list of string is empty
    public boolean isEmpty() {
        return true;
    }

    // to merge this sorted list with the given sorted list
    public ILoString merge(ILoString that, IStringsCompare comp) {
        return that;
    }

    // to compare two ConsLoStrings
    public ConsLoString mergeHelp(ConsLoString los, IStringsCompare comp) {
        throw new RuntimeException("No ConsLoString of runners available at this time!");
    }

    // to produce a sorted list given by the IStringsCompare method
    public ILoString sort(IStringsCompare comp) {
        return this;
    }

    // to insert a string into a sorted list
    public ILoString insert(String s, IStringsCompare comp) {
        return new ConsLoString(s, this);
    }

    // to determine if this list of strings and the given list of strings
    // are the same data in the same order
    public boolean sameList(ILoString that) {
        return that.isEmpty();   
    }

    // helper function for sameList method
    public boolean sameListHelp(ConsLoString that) {
        return false;
    }
}


//to represent a nonempty list of Strings
class ConsLoString implements ILoString {
    String first;
    ILoString rest;

    // the constructor
    ConsLoString(String first, ILoString rest) {
        this.first = first;
        this.rest = rest;  
    }

    // determine whether a list of string is sorted
    public boolean isSorted(IStringsCompare comp) {
        return this.rest.pullSecond(this.first, comp) &&
                this.rest.isSorted(comp);
    }

    // compares the first string in the list with the second string in the list
    public boolean pullSecond(String s, IStringsCompare comp) {
        return comp.comesBefore(s, this.first);
    }

    // to determine if this list of string is empty
    public boolean isEmpty() {
        return false;
    }

    // to merge this sorted list with the given sorted list
    public ILoString merge(ILoString that, IStringsCompare comp) {
        if (that.isEmpty()) {
            return this;
        }
        else {
            return that.mergeHelp(this, comp);
        }
    }

    // to compare two ConsLoStrings
    public ConsLoString mergeHelp(ConsLoString los, IStringsCompare comp) {
        if (comp.comesBefore(this.first, los.first)) {
            return new ConsLoString(this.first, 
                    this.rest.merge(los, comp));
        }
        else {
            return new ConsLoString(los.first, this.merge(los.rest, comp));
        }
    }

    // to produce a sorted list given by the IStringsCompare method
    public ILoString sort(IStringsCompare comp) {
        return this.rest.sort(comp).insert(this.first, comp);
    }

    // to insert a string into a sorted list
    public ILoString insert(String s, IStringsCompare comp) {
        if (comp.comesBefore(s, this.first)) {
            return new ConsLoString(s, 
                    this.rest.insert(this.first, comp));
        }
        else {
            return new ConsLoString(this.first, 
                    this.rest.insert(s, comp));
        }
    }
    
    // to determine if this list of strings and the given list of strings
    // are the same data in the same order
    public boolean sameList(ILoString that) {
        if (that.isEmpty()) {
            return false;
        }
        else {
            return this.sameListHelp((ConsLoString)that); 
        }
    }

    // helper function for sameList method
    public boolean sameListHelp(ConsLoString that) {
        return this.first.equals(that.first) && this.rest.sameList(that.rest);

    }
}

// to represent the interface IStringsCompare
interface IStringsCompare {

    // to compare which string comes before
    boolean comesBefore(String s1, String s2);

}

// to compare the Strings lexicographically
class StringLexComp implements IStringsCompare {

    // to compare which string comes before
    public boolean comesBefore(String s1, String s2) {
        return s1.compareTo(s2) < 0;
    }
}

// represent a class to compare string length
class StringLengthComp implements IStringsCompare {
    // to compare the strings by their lengths from the shortest
    // to the longest
    public boolean comesBefore(String s1, String s2) {
        return s1.length() < s2.length();
    }
}

// to represent an example class
class ExamplesStrings {

    String s1 = "Happy";
    String s2 = "AH";
    String s3 = "Banana";
    String s4 = "Cup";

    ILoString mt = new MtLoString();
    ILoString unsorted1 = new ConsLoString(this.s1, new ConsLoString(this.s2, 
            new ConsLoString(this.s3, this.mt)));
    ILoString unsorted2 = new ConsLoString(this.s3, new ConsLoString(this.s2, 
            new ConsLoString(this.s1, this.mt)));
    ILoString unsorted3 = new ConsLoString(this.s1, new ConsLoString(this.s2, 
            new ConsLoString(this.s3, new ConsLoString(this.s4, this.mt))));
    ILoString unsorted4 = new ConsLoString(this.s3, new ConsLoString(this.s4, this.mt));
    ILoString lexSorted1 = new ConsLoString(this.s2, new ConsLoString(this.s3, 
            new ConsLoString(this.s4, new ConsLoString(this.s1, this.mt))));
    ILoString lengthSorted1 = new ConsLoString(this.s2, 
            new ConsLoString(this.s4,
            new ConsLoString(this.s1, new ConsLoString(this.s3, this.mt))));
    ILoString lexSorted2 = new ConsLoString(this.s2, 
            new ConsLoString(this.s1, this.mt));
    ILoString lengthSorted2 = new ConsLoString(this.s4, 
            new ConsLoString(this.s3, this.mt));
    
    ConsLoString consLex1 = new ConsLoString(this.s2, new ConsLoString(this.s3, 
            new ConsLoString(this.s4, new ConsLoString(this.s1, this.mt))));
    ConsLoString consLex2 = new ConsLoString(this.s2, 
            new ConsLoString(this.s1, this.mt));
    ConsLoString consLength1 = new ConsLoString(this.s2, 
            new ConsLoString(this.s4,
            new ConsLoString(this.s1, new ConsLoString(this.s3, this.mt))));
    ConsLoString consLength2 = new ConsLoString(this.s4, 
            new ConsLoString(this.s3, this.mt));

    // to test the method comesBefore
    boolean testComesBefore(Tester t) {
        return t.checkExpect(new StringLexComp().comesBefore(s1, s2), false) &&
                t.checkExpect(new StringLengthComp().comesBefore(s2, s1), true);
    }

    // to test the method isSorted                              
    boolean testIsSorted(Tester t) {
        return t.checkExpect(this.mt.isSorted(new StringLexComp()), true) &&
                t.checkExpect(this.mt.isSorted(new StringLengthComp()), true) &&
                t.checkExpect(this.unsorted1.isSorted(new StringLexComp()), false) &&
                t.checkExpect(this.unsorted2.isSorted(new StringLengthComp()), false) &&
                t.checkExpect(this.lexSorted1.isSorted(new StringLexComp()), true) &&
                t.checkExpect(this.lengthSorted1.isSorted(new StringLengthComp()), true);
    }

    // to test the method isEmpty
    boolean testIsEmpty(Tester t) {
        return t.checkExpect(this.mt.isEmpty(), true) &&
                t.checkExpect(this.unsorted1.isEmpty(), false) &&
                t.checkExpect(this.lexSorted1.isEmpty(), false);
    }

    // to test the method merge
    boolean testMerge(Tester t) {
        return t.checkExpect(this.mt.merge(this.mt, 
                new StringLexComp()), this.mt) &&
                t.checkExpect(this.mt.merge(this.lexSorted1, 
                        new StringLexComp()), this.lexSorted1) &&
                t.checkExpect(this.mt.merge(this.lengthSorted1, 
                        new StringLengthComp()), this.lengthSorted1) &&
                t.checkExpect(this.lexSorted2.merge(this.mt, 
                        new StringLexComp()), this.lexSorted2) &&
                t.checkExpect(this.lexSorted1.merge(this.lexSorted2, new StringLexComp()),
                        new ConsLoString(this.s2, new ConsLoString(this.s2, 
                                new ConsLoString(this.s3, new ConsLoString(this.s4, 
                                        new ConsLoString(this.s1, 
                                                new ConsLoString(this.s1, this.mt))))))) &&
                t.checkExpect(this.lengthSorted1.merge(this.lengthSorted2, 
                        new StringLengthComp()), 
                        new ConsLoString(this.s2, new ConsLoString(this.s4,
                                new ConsLoString(this.s4, 
                                        new ConsLoString(this.s1, 
                                        new ConsLoString(this.s3, 
                                                new ConsLoString(this.s3, this.mt)))))));
    }

    // to test the method mergeHelp()
    boolean testMergeHelp(Tester t) {
        return //TODO 
               // t.checkException(this.mt.mergeHelp(this.lexSorted1, 
               // new StringLexComp()), 
               // "No ConsLoString of runners available at this time!") &&
                t.checkExpect(this.consLex2.mergeHelp(this.consLex1, 
                        new StringLexComp()), 
                        new ConsLoString(this.s2, new ConsLoString(this.s2,
                                new ConsLoString(this.s3, new ConsLoString(this.s4,
                                        new ConsLoString(this.s1, new ConsLoString(this.s1,
                                                this.mt))))))) &&
                t.checkExpect(this.consLength1.mergeHelp(this.consLength2, 
                        new StringLengthComp()), 
                        new ConsLoString(this.s2, new ConsLoString(this.s4,
                                new ConsLoString(this.s4,
                                        new ConsLoString(this.s1, 
                                                new ConsLoString(this.s3, 
                                                        new ConsLoString(this.s3, this.mt)))))));
    }

    
    // to test the method pullSecond()
    boolean testPullSecond(Tester t) {
        return t.checkExpect(this.mt.pullSecond(this.s1, 
                new StringLexComp()), true) &&
                t.checkExpect(this.mt.pullSecond(this.s1, 
                        new StringLengthComp()), true) &&
                t.checkExpect(this.lexSorted1.pullSecond(this.s2, 
                        new StringLexComp()), false) &&
                t.checkExpect(this.lexSorted2.pullSecond(this.s3, 
                        new StringLexComp()), false) &&
                t.checkExpect(this.lengthSorted2.pullSecond(this.s2, 
                        new StringLengthComp()), true);
    }
    
    // to test the sort method
    boolean testSort(Tester t) {
        return t.checkExpect(this.mt.sort(new StringLexComp()), this.mt) &&
                t.checkExpect(this.mt.sort(new StringLengthComp()), this.mt) &&
                t.checkExpect(this.unsorted3.sort(new StringLexComp()), this.lexSorted1) &&
                t.checkExpect(this.unsorted4.sort(new StringLengthComp()), this.lengthSorted2);
    }
    

    // to test the insert method
    boolean testInsert(Tester t) {
        return t.checkExpect(this.mt.insert(this.s1, new StringLexComp()),
                new ConsLoString(this.s1, this.mt)) &&
                t.checkExpect(this.mt.insert(this.s2, new StringLengthComp()),
                        new ConsLoString(this.s2, this.mt)) &&
                t.checkExpect(this.lexSorted1.insert(this.s1, new StringLexComp()),
                        new ConsLoString(this.s2, new ConsLoString(this.s3, 
                                new ConsLoString(this.s4, new ConsLoString(this.s1, 
                                        new ConsLoString(this.s1, this.mt)))))) &&
                t.checkExpect(this.lengthSorted1.insert(this.s2, new StringLengthComp()),
                        new ConsLoString(this.s2, new ConsLoString(this.s2, 
                                new ConsLoString(this.s4, new ConsLoString(this.s1, 
                                    new ConsLoString(this.s3, this.mt))))));
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

