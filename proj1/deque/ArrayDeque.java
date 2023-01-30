package deque;
import java.util.Iterator;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int addOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        int index = addOne(nextFirst);
        for(int i = 0; i < size; i++) {
            a[i] = items[index];
            index = addOne(index);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    private void checkDiv(T[] items) {
        if ((size >= 16) && (size < items.length / 4)) {
            resize(items.length / 4);
        }
    }

    private void checkMu(T[] items) {
        if(size == items.length) {
            resize(size * 2);
        }
    }


    @Override
    public void addFirst(T item) {
        checkMu(items);
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size ++;
    }

    @Override
    public void addLast(T item) {
        checkMu(items);
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size ++;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index = addOne(nextFirst);
        for(int i = 0; i < size; i++) {
            System.out.print(items[i] + "");
            index = addOne(index);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {

        if (size == 0) {
            return null;
        }
        checkDiv(items);

        addOne(nextFirst);
        T t = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return t;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        checkDiv(items);

        minusOne(nextLast);
        T t = items[nextLast];
        items[nextLast] = null;
        size--;
        return t;
    }

    @Override
    public T get(int index) {
        return items[(index + nextFirst + 1) % items.length];
    }


    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    protected class ArrayDequeIterator implements Iterator<T> {
        private int ptr;

        ArrayDequeIterator() {
            ptr = addOne(nextFirst);
        }
        public boolean hasNext() {
            return ptr != nextLast;
        }
        public T next() {
            T item =  items[ptr];
            ptr = addOne(ptr);
            return item;
        }
    }

    /**
     * Returns whether or not the parameter o is equal to the Deque.
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as goverened by the generic T’s equals method) in the same order.
     * (ADDED 2/12: You’ll need to use the instance of keywords for this.)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque) o;
        if (size != other.size()) {
            return false;
        }

        int index = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            if (!(items[index].equals(other.get(i)))) {
                return false;
            }
            index = addOne(index);
        }
        return true;
    }
}
