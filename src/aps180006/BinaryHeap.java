/**
 *
 * @author Akash Akki net id:apa190001
 * @author Anant Srivastava net id : aps180006
 */


package aps180006;


import java.util.NoSuchElementException;
import java.util.Scanner;
public class BinaryHeap<T extends Comparable<? super T>> {
    Comparable[] pq;
    int size;

    // Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(int maxCapacity) {
        pq = new Comparable[maxCapacity];
        size = 0;
    }

    // add method: resize pq if needed

    /**
     * @param x Element to be added to Binary Heap
     * @return return true if element is added otherwise false
     */
    public boolean add(T x) {


        if (this.size == pq.length) {
            this.resize();
        }
        pq[this.size] = x;
        percolateUp(this.size);
        this.size++;
        System.out.println("size is "+size);
        return true;
    }

    /**
     * @param x Element to be added
     * @return returns true if element is added otherwise false
     */
    public boolean offer(T x) {
        return add(x);
    }

    // throw exception if pq is empty

    /**
     * @return returns the min of the heap i.e root of the binary heap and the element is removed
     * @throws NoSuchElementException
     */
    public T remove() throws NoSuchElementException {
        T result = poll();
        if (result == null) {
            throw new NoSuchElementException("Priority queue is empty");
        } else {
            return result;
        }
    }

    // return null if pq is empty

    /**
     * @return returns min of the binary heap i.e root of binary heap and the element is removed
     */
    public T poll() {
        if (this.size > 0) {
            T min = (T) pq[0];
            pq[0] = pq[this.size - 1];
            size--;
            percolateDown(0);
            return min;
        } else {
            return null;
        }
    }

    /**
     * @return return minimum element but does not remove from the heap
     */
    public T min() {
        return peek();
    }

    // return null if pq is empty

    /**
     * @return returns root element of binary heap
     */
    public T peek() {
        if (this.size <= 0)
            return null;
        return (T) pq[0];
    }

    /**
     * @param i input index
     * @return return parent of the given index
     */
    int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * @param i input index
     * @return return leftchild of the given index
     */
    int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * @param i input index
     * @return return right child of the given index
     */
    int rightChild(int i) {
        return 2 * i + 2;
    }

    /** pq[index] may violate heap order with parent */
    /**
     * @param index input index
     */
    void percolateUp(int index) {
        Comparable x = pq[index];
        while (index > 0 && (compare(pq[parent(index)], x) == 1)) {
            pq[index] = pq[parent(index)];
            index = parent(index);
        }
        pq[index] = x;
    }

    /** pq[index] may violate heap order with children */
    /**
     * @param index input index
     */
    void percolateDown(int index) {
        int l = leftChild(index);
        int r = rightChild(index);
        int smallest = index;
        if (l < size && compare(pq[l], pq[index]) == -1) {
            smallest = l;
        }
        if (r < size && compare(pq[r], pq[smallest]) == -1) {
            smallest = r;
        }
        if (index != smallest) {
            Comparable temp = pq[smallest];
            pq[smallest] = pq[index];
            pq[index] = temp;
            percolateDown(smallest);
        }
    }

    void move(int dest, Comparable x) {
        pq[dest] = x;
    }

    /**
     * @param a input object a
     * @param b input object b
     * @return return -1 if a is smaller, returns 1 if b is smaller otherwise 0
     */
    int compare(Comparable a, Comparable b) {
        return ((T) a).compareTo((T) b);
    }

    /**
     * Create a heap.  Precondition: none.
     */
    void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * @return true if heap is empty otherwise false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return returns size of the heap
     */
    public int size() {
        return size;
    }

    // Resize array to double the current size
    void resize() {
        Comparable[] temp = new Comparable[size * 2];
        for (int i = 0; i < pq.length; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void printBinaryHeap() {
        System.out.print((size + 1) + ": ");
        for (int i = 0; i < size; i++) {
            System.out.print(pq[i] + " ");
        }
        System.out.println();
    }
//***	(Not implemented)
//    public interface Index {
//        public void putIndex(int index);
//        public int getIndex();
//    }
//	public static class IndexedHeap<T extends Index & Comparable<? super T>> extends BinaryHeap<T> {
//        /** Build a priority queue with a given array */
//        IndexedHeap(int capacity) {
//            super(capacity);
//        }
//
//        /** restore heap order property after the priority of x has decreased */
//        void decreaseKey(T x) {
//        }
//
//        @Override
//        void move(int i, Comparable x) {
//            super.move(i, x);
//        }
//    }
//***

    public static void main(String[] args) {
        Integer[] arr = new Integer[5];
        BinaryHeap<Integer> h = new BinaryHeap(arr.length);

        Scanner sc = new Scanner(System.in);
        whileloop:
        while (sc.hasNext()) {
            int x;
            switch (sc.nextInt()) {
                case 1: // Offer
                    System.out.println("insert element");
                    x = sc.nextInt();
                    h.offer(x);
                    h.printBinaryHeap();
                    break;
                case 2: // Add
                    System.out.println("insert element");
                    x = sc.nextInt();
                    h.add(x);
                    h.printBinaryHeap();
                    break;
                case 3: // Poll
                    System.out.println(h.poll());
                    h.printBinaryHeap();
                    break;
                case 4: // Remove
                    System.out.println(h.remove());
                    h.printBinaryHeap();
                    break;
                case 5: // Peek
                    System.out.println(h.peek());
                    break;
                default:
                    break whileloop;
            }
        }
    }
}
