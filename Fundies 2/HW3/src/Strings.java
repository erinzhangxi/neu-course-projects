// CS 2510 Fall 2015
// Assignment 3

// Zhang Xi
// xizhang2
// Laniado Jonathan
// laniadoj

import tester.*;

// to represent a list of Strings
interface ILoString {
    
    // combine all Strings in this list into one
    String combine();
    
    // to produce a new list sorted in alphabetical order
    ILoString sort();
    
    // to insert the given String into the list of Strings 
    // already sorted alphabetically
    ILoString insert(String that);
    
    // to determine if the list is sorted in alphabetical 
    // order, in a case-insensitive-way
    boolean isSorted();
    
    // to compare if the first and the second String in the list
    // are in alphabetical order    
    public boolean isSortedHelp(String s);
    
    // to take the first, third, fifth...elements from this list,
    // and take the second, fourth, sixth... elements from the given 
    // list, and produce a new list of Strings
    ILoString interleave(ILoString that); 
    
    // to produce a sorted list of Strings that contains a sorted given list
    // of Strings and a given sorted list of Strings
    ILoString merge(ILoString that);
    
    // to pass in the accumulator for reverseHelp method
    ILoString reverse();
    
    // to reverse this list of Strings
    ILoString reverseHelp(ILoString acc);
    
    // to pass in that String and the accumulator to the isDoubleListHelp method
    boolean isDoubledList();
    
    // to determine if this list contains pairs of identical strings
    boolean isDoubledListHelp(String that, boolean acc);
    
    // to compute the length of this list of Strings
    int listSize();
    
    // to determine if this list of Strings contains the same words reading
    // the list in either order
    boolean isPalindromeList();
}


// to represent an empty list of Strings
class MtLoString implements ILoString {
    
    // combine all Strings in this list into one
    public String combine() {
        return "";
    }  
    
    // to produce a new list sorted in alphabetical order
    public ILoString sort() {
        return this;
    }
    
    // to insert the given String into the empty list of Strings 
    public ILoString insert(String that) {
        return new ConsLoString(that, new MtLoString());
    }
    
    // to determine if this empty list is sorted in alphabetical 
    // order, in a case-insensitive-way
    public boolean isSorted() {
        return true;
    }
    
    // to compare if the first and the second String in the list
    // are in alphabetical order    
    public boolean isSortedHelp(String s) {
        return true;
    }  
    
    // to take the first, third, fifth...elements from this list,
    // and take the second, fourth, sixth... elements from the given 
    // list, and produce a new list of Strings
    public ILoString interleave(ILoString that) {
        return that;
    }
    
    // to produce a sorted list of Strings that contains a sorted given list
    // of Strings and a given sorted list of Strings
    public ILoString merge(ILoString that) {
        return that;
    }
    
    // to pass in the accumulator for reverseHelp method
    public ILoString reverse() {
        return this;
    }
    
    // to reverse this list of Strings
    public ILoString reverseHelp(ILoString acc) {
        return acc; 
    }
    
    // to pass in that String and the accumulator to the isDoubleListHelp method
    public boolean isDoubledList() {
        return true;
    }
    
    // to determine if this list contains pairs of identical strings
    public boolean isDoubledListHelp(String that, boolean acc) {
        return acc;
    }
    
    // to compute the length of this list of Strings
    public int listSize() {
        return 0;
    }
    
    // to determine if this list of Strings contains the same words reading
    // the list in either order
    public boolean isPalindromeList() {
        return true;
    }
}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
    String first;
    ILoString rest;
    
    ConsLoString(String first, ILoString rest) {
        this.first = first;
        this.rest = rest;  
    }
   
    // combine all Strings in this list into one
    public String combine() {
        return this.first.concat(this.rest.combine());
    }  
    
    // to produce a new list sorted in alphabetical order
    public ILoString sort() {
        return this.rest.sort().insert(this.first);
    }   

    // to insert the given String into the empty list of Strings 
    public ILoString insert(String that) {
        if (this.first.toLowerCase().compareTo(that.toLowerCase()) < 0) {
            return new ConsLoString(this.first, 
                    new ConsLoString(that, this.rest.sort()));
        }
        else { 
            return new ConsLoString(that, 
                    this.rest.insert(this.first));
        }
    }

    // to determine if this empty list is sorted in alphabetical 
    // order, in a case-insensitive-way
    public boolean isSorted() {
        return this.rest.isSortedHelp(this.first) &&
                this.rest.isSorted();
    }
    
    // to compare if the first and the second String in the list
    // are in alphabetical order    
    public boolean isSortedHelp(String s) {
        return s.toLowerCase().compareTo(this.first.toLowerCase()) < 0;
    }     
    
    // to take the first, third, fifth...elements from this list,
    // and take the second, fourth, sixth... elements from the given 
    // list, and produce a new list of Strings
    public ILoString interleave(ILoString that) {
        return new ConsLoString(this.first, that.interleave(this.rest));
    }
    
    
    // to produce a sorted list of Strings that contains a sorted given list
    // of Strings and a given sorted list of Strings
    public ILoString merge(ILoString that) {
        return this.interleave(that).sort();
    }
    
    
    /* without interleave()
    // to produce a sorted list of Strings that contains a sorted given list
    // of Strings and a given sorted list of Strings
    public ILoString merge(ILoString that) {
        if (that.isEmpty()) { 
            return this;
        }
        else {
            return this.mergeHelp((ConsLoString)that);
        }
    }
    
    public ILoString mergeHelp(ConsLoString los) {
        if (this.first.toLowerCase().compareTo(los.first.toLowerCase()) < 0) {
            return new ConsLoString(this.first, this.rest.merge(los));
        }
        else {
            return new ConsLoString(los.first, this.merge(los.rest));
        }
    }
    */
    
    
    // to pass in the accumulator for reverseHelp method
    public ILoString reverse() {
        return this.reverseHelp(new MtLoString());
    }
    
    // to reverse this list of Strings
    public ILoString reverseHelp(ILoString acc) {
        return this.rest.reverseHelp(new ConsLoString(this.first, acc)); 
    }
    
    // to pass in that String and the accumulator to the isDoubleListHelp method
    public boolean isDoubledList() {
        if (this.listSize() % 2 != 0) {
            return false;
        }
        else {
            return this.rest.isDoubledListHelp(this.first, false);
        }
    }
    
    // to determine if this list contains pairs of identical strings
    public boolean isDoubledListHelp(String that, boolean acc) {
        return this.rest.isDoubledListHelp(this.first, this.first.equals(that) || acc);
    }    
    
    // to compute the length of this list of Strings
    public int listSize() {
        return 1 + this.rest.listSize();
    }
    
    // to determine if this list of Strings contains the same words reading
    // the list in either order
    public boolean isPalindromeList() {
        return this.combine().equals(this.reverse().combine());
    }
}

// to represent examples for lists of strings
class ExamplesStrings {
    
    ILoString mary = new ConsLoString("Mary ",
                    new ConsLoString("had ",
                        new ConsLoString("a ",
                            new ConsLoString("little ",
                                new ConsLoString("lamb.", new MtLoString())))));
    
    ILoString mtLoString = new MtLoString();
    
    ILoString sorted = new ConsLoString("a", 
            new ConsLoString("c",
                    new ConsLoString("d",
                            new ConsLoString("g", this.mtLoString)))); 
    
    ILoString sorted2 = new ConsLoString("a", 
            new ConsLoString("f", 
                    new ConsLoString("g",
                            new ConsLoString("h", this.mtLoString))));
   
    ILoString sorted3 = new ConsLoString("b", 
            new ConsLoString("d", 
                    new ConsLoString("f",
                            new ConsLoString("h", this.mtLoString))));
    
    ILoString unsorted = new ConsLoString("Book ", 
                      new ConsLoString("and ",
                         new ConsLoString("Pen ", 
                            new ConsLoString("Water ", new MtLoString()))));
    ILoString unsorted2 = new ConsLoString("All", new ConsLoString("Broken",
            new ConsLoString("any", new ConsLoString("before", this.mtLoString))));
    
    ILoString color = new ConsLoString("black",
            new ConsLoString("white",
                    new ConsLoString("yellow",
                            new ConsLoString("grey", 
                                    new ConsLoString("red",
                                         new ConsLoString("purple", this.mtLoString))))));  
    
    ILoString palindrome = new ConsLoString("a",
            new ConsLoString("b",
                    new ConsLoString("c",
                            new ConsLoString("b", 
                                    new ConsLoString("a", this.mtLoString))))); 
                           
    ILoString oneString = new ConsLoString("a", this.mtLoString);
    
    ILoString doubleList = new ConsLoString("a", new ConsLoString("a",
            new ConsLoString("c", this.mtLoString)));
    
    ILoString notDoubleList = new ConsLoString("a", new ConsLoString("c", 
            new ConsLoString("a", this.mtLoString)));
    
    ILoString notDoubleList2 = new ConsLoString("a", new ConsLoString("a",
            new ConsLoString("a", this.mtLoString))); 
    
    // test the method combine for the lists of Strings
    boolean testCombine(Tester t) {
        return t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
    }
    
    // test the method sort for the lists of Strings
    boolean testSort(Tester t) {
        return t.checkExpect(this.mtLoString.sort(), this.mtLoString)
                && t.checkExpect(this.unsorted.sort(), 
                        new ConsLoString("and ", 
                                new ConsLoString("Book ",
                                new ConsLoString("Pen ", 
                                        new ConsLoString("Water ", this.mtLoString)))));
    }
    
    // test the method insert for the lists of Strings
    boolean testInsert(Tester t) {
        return t.checkExpect(this.mtLoString.insert("a"), 
                new ConsLoString("a", this.mtLoString))
                && t.checkExpect(this.sorted.insert("b"), 
                        new ConsLoString("a",
                                new ConsLoString("b",
                                        new ConsLoString("c",
                                                new ConsLoString("d",
                                                        new ConsLoString("g", this.mtLoString))))));

    }
    
    // test the method isSorted for the lists of Strings
    boolean testIsSorted(Tester t) {
        return t.checkExpect(this.mtLoString.isSorted(), true)
                && t.checkExpect(this.sorted.isSorted(), true)
                && t.checkExpect(this.unsorted.isSorted(), false)
                && t.checkExpect(this.unsorted2.isSorted(), false);
    }
    
    // test the method interleave for the lists of Strings
    boolean testInterleave(Tester t) {
        return t.checkExpect(this.mtLoString.interleave(this.mtLoString), 
                this.mtLoString)
                && t.checkExpect(this.mtLoString.interleave(this.sorted),
                        this.sorted)
                && t.checkExpect(this.sorted.interleave(this.unsorted),
                        new ConsLoString("a", 
                                new ConsLoString("Book ", 
                                        new ConsLoString("c",
                                new ConsLoString("and ", 
                                        new ConsLoString("d",
                                                new ConsLoString("Pen ", 
                                                        new ConsLoString("g", 
                                                new ConsLoString("Water ", 
                                                        this.mtLoString)))))))))
                && t.checkExpect(this.sorted.interleave(this.color), 
                        new ConsLoString("a", 
                        new ConsLoString("black", 
                                new ConsLoString("c", new ConsLoString("white",
                                    new ConsLoString("d", new ConsLoString("yellow", 
                                        new ConsLoString("g",
                                            new ConsLoString("grey", new ConsLoString("red",
                                                new ConsLoString("purple", 
                                                        this.mtLoString)))))))))));
    }
    
    // test the method merge for the lists of Strings
    boolean testMerge(Tester t) {
        return t.checkExpect(this.mtLoString.merge(this.sorted), 
                new ConsLoString("a", 
                        new ConsLoString("c",
                                new ConsLoString("d",
                                        new ConsLoString("g", this.mtLoString)))))
        // an example when merge and interleave produce different results
                && t.checkExpect(this.sorted.merge(this.sorted2), 
                    new ConsLoString("a", new ConsLoString("a", new ConsLoString("c", 
                        new ConsLoString("d", new ConsLoString("f", new ConsLoString("g", 
                                new ConsLoString("g", new ConsLoString("h",
                                    this.mtLoString)))))))))
                // an example when merge and interleave produce the same results
                && t.checkExpect(this.sorted.merge(this.sorted3),
                        new ConsLoString("a", new ConsLoString("b", new ConsLoString("c", 
                                new ConsLoString("d", new ConsLoString("d", 
                                        new ConsLoString("f", new ConsLoString("g", 
                                            new ConsLoString("h", this.mtLoString)))))))));
    }
    
    // test the method reverse for the lists of Strings
    boolean testReverse(Tester t) {
        return t.checkExpect(this.mtLoString.reverse(), this.mtLoString)
                && t.checkExpect(this.palindrome.reverse(), 
                        this.palindrome)
                && t.checkExpect(this.sorted.reverse(), 
                        new ConsLoString("g", 
                                new ConsLoString("d",
                                        new ConsLoString("c",
                                                new ConsLoString("a", this.mtLoString)))));
    }
    
    // test the method isDoubledList for the lists of Strings
    boolean testIsDoubledList(Tester t) {
        return t.checkExpect(this.mtLoString.isDoubledList(), true)
                && t.checkExpect(this.sorted.isDoubledList(), false)
                && t.checkExpect(this.oneString.isDoubledList(), false)
                && t.checkExpect(this.notDoubleList.isDoubledList(), false)
                && t.checkExpect(this.doubleList.isDoubledList(), false)
                && t.checkExpect(this.notDoubleList2.isDoubledList(), false);
    }
    
    // test the method isPalindromeList for the lists of Strings
    boolean testIsPalindromeList(Tester t) {
        return t.checkExpect(this.mtLoString.isPalindromeList(), true)
                && t.checkExpect(this.sorted.isPalindromeList(), false)
                && t.checkExpect(this.palindrome.isPalindromeList(), true);
    }
    
}
