import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

// TODO higher priority. can we assume given node is not a leaf?

interface Comparator<T> {
    int compare(T t1, T t2);
}

// determine if t1 is less than or equal to t2
class BiggerThan implements Comparator<Integer> {
    public int compare(Integer t1, Integer t2) {
        return t1 - t2;
    }
}

class PriorityQueue<T> {
    ArrayList<T> alist;
    Comparator<T> comp;

    PriorityQueue(ArrayList<T> alist, Comparator<T> comp) {
        this.alist = alist;
        this.comp = comp;
    }

    // determines if the node has no children
    boolean isLeaf(int idx) {
        if (idx > this.alist.size() - 1) {
            throw new RuntimeException("Index out of bound!");
        }
        else {
            return 2 * idx + 1 >= this.alist.size();
        }
    }

    // returns the index of a given node's child with the highest
    // priority
    int higherPriorityChild(int idx) {
        int rightChildIdx = 2 * idx + 2;
        int leftChildIdx = 2 * idx + 1;

        if (idx >= this.alist.size() - 1) {    // TODO  >= or >
            throw new RuntimeException("No more Node or Leaf!");
        }
        // when there is only one leaf on the left 
        else if (this.alist.size() == rightChildIdx) {
            return leftChildIdx;
        }
        else {
            T parentLeft = this.alist.get(leftChildIdx);
            T parentRight = this.alist.get(rightChildIdx);
            if (this.comp.compare(parentRight, parentLeft) <= 0) {
                return leftChildIdx;
            }
            else {
                return rightChildIdx;
            }
        }
    }

    // to swap two items
    void swap(int idx1, int idx2) {
        T oldValue1 = this.alist.get(idx1);
        T oldValue2 = this.alist.get(idx2);

        this.alist.set(idx1, oldValue2);
        this.alist.set(idx2, oldValue1);
    }

    // to insert a new node into the heap
    void insert(T t) {
        this.alist.add(t);
        int parentIdx = (int)(Math.floor(this.alist.indexOf(t) - 1) / 2);
        T parent = this.alist.get(parentIdx);

        // when parent is less than t 
        while (this.comp.compare(parent, t) < 0 &&
                parentIdx != this.alist.indexOf(t)) {
            this.swap(parentIdx, alist.indexOf(t));
            parentIdx = (int)(Math.floor(this.alist.indexOf(t) - 1) / 2);
        }
    }

    // to remove an item from a heap
    void remove() {
        this.alist.set(0, alist.get(this.alist.size() - 1));
        this.alist.remove(this.alist.size() - 1);
        // when there is one or no element in the list
        if (this.alist.size() - 1 <= 0) {
        // do nothing
        }
        else {
            int rootIdx = 0;
            T root = this.alist.get(rootIdx);

            int childIdx = this.higherPriorityChild(rootIdx);
            T child = this.alist.get(childIdx);

            // when parent is less than t 
            while (this.comp.compare(root, child) < 0 &&
                    !this.isLeaf(rootIdx)) {

                this.swap(childIdx, rootIdx);
                rootIdx = childIdx;

                if (!this.isLeaf(rootIdx)) {
                    childIdx = this.higherPriorityChild(rootIdx);
                    child = this.alist.get(childIdx);
                    root = this.alist.get(rootIdx); // TODO necessary to update root?
                }
            }
        }
    }
}


class HeapSort {
    // given a comparator and an array list, sort the 
    // array list
    public <T> ArrayList<T> heapsort(ArrayList<T> alist, Comparator<T> comp) {

        ArrayList<T> mtHeap = new ArrayList<T>();
        PriorityQueue<T> unsorted = new PriorityQueue<T>(mtHeap, comp);

        ArrayList<T> result = new ArrayList<T>();
        T top;

        for (int i = 0; i < alist.size(); i = i + 1) {
            unsorted.insert(alist.get(i));
        }

        for (int i = 0; i < alist.size(); i = i + 1) {

            top = unsorted.alist.get(0); 
            unsorted.remove(); 
            result.add(top);
        }
        return result;
    }
}


class ExamplesHeaps {
    ArrayList<Integer> heap1 = new ArrayList<Integer>();
    ArrayList<Integer> heap2 = new ArrayList<Integer>();
    ArrayList<Integer> heap3 = new ArrayList<Integer>();
    PriorityQueue<Integer> pque1 = new PriorityQueue<Integer>(null, null);
    //  PriorityQueue<Integer> pque2 = new PriorityQueue<Integer>(null, null);

    ArrayList<Integer> sortedArray1 =
            new ArrayList<Integer>(Arrays.asList(80, 60, 50, 50, 40, 30, 20));

    void initHeaps() {
        heap1 = new ArrayList<Integer>(Arrays.asList(80, 60, 50, 30, 50, 40, 20));
        heap2 = new ArrayList<Integer>(Arrays.asList(15, 10, 5, 50, 80, 20, 30));
        heap3 = new ArrayList<Integer>(Arrays.asList(50, 30, 40, 20, 10, 10, 70, 80));
        pque1 = new PriorityQueue<Integer>(this.heap1, biggerThan);
        //     pque2 = new PriorityQueue<Integer>(this.heap2, lessThan);
    }

    Comparator<Integer> biggerThan = new BiggerThan();
    HeapSort hp = new HeapSort();

    void testIsLeaf(Tester t) {
        this.initHeaps();

        t.checkExpect(pque1.isLeaf(0), false);
        t.checkExpect(pque1.isLeaf(4), true);
        t.checkExpect(pque1.isLeaf(5), true);
        t.checkExpect(pque1.isLeaf(1), false);
        t.checkException(new RuntimeException("Index out of bound!"),
                pque1, "isLeaf", 10);
    }

    void testHigherPriorityChild(Tester t) {
        this.initHeaps();
        t.checkExpect(pque1.higherPriorityChild(0), 1);
        t.checkExpect(pque1.higherPriorityChild(1), 4);
        t.checkException(new RuntimeException("No more Node or Leaf!"),
                pque1, "higherPriorityChild", 10);
    }

    void testInsert(Tester t) {
        this.initHeaps();
        pque1.insert(20);
        t.checkExpect(heap1.get(7), 20);

        pque1.insert(90);
        t.checkExpect(heap1.get(0), 90);
        t.checkExpect(heap1.get(1), 80);
        t.checkExpect(heap1.size(), 9);
    }

    void testRemove(Tester t) {
        this.initHeaps();
        this.pque1.remove();
        t.checkExpect(this.heap1.get(0), 60);
        t.checkExpect(this.heap1.size(), 6);
        t.checkExpect(this.heap1.get(5), 40);
        this.pque1.remove();
        t.checkExpect(this.heap1.get(0), 50);
        t.checkExpect(this.heap1.size(), 5);
        t.checkExpect(this.heap1.get(4), 20);
        this.pque1.remove();
        t.checkExpect(this.heap1.get(0), 50);
        t.checkExpect(this.heap1.size(), 4);
        t.checkExpect(this.heap1.get(3), 30);  
        t.checkExpect(this.heap1.get(2), 20);
        t.checkExpect(this.heap1.get(1), 40);
        this.pque1.remove();
        t.checkExpect(this.heap1.get(0), 40);
        t.checkExpect(this.heap1.size(), 3);
        t.checkExpect(this.heap1.get(2), 20);
        this.pque1.remove();
        t.checkExpect(this.heap1.get(0), 30);
        t.checkExpect(this.heap1.size(), 2);
        t.checkExpect(this.heap1.get(1), 20);
    }

    void testHeapSort(Tester t) {
        this.initHeaps();
        t.checkExpect(this.hp.heapsort(this.heap1, biggerThan), this.sortedArray1);
        // t.checkExpect(hp.heapsort(heap1, lessThan), pque1.alist);


    }
}