package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{

    public class IntNode {
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(T i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size ++;
    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(item, sentinel.prev,sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size ++;
    }

    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String[] strings = new String[size];
        IntNode p = sentinel.next;
        if (p == sentinel) {
            return;
        }
        for(int i = 0; i < size; i++) {
            strings[i] = p.item.toString();
            p = p.next;
        }
        System.out.print(String.join(" ", strings));
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }else {
            T t = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return t;
        }

    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }else {
            T t = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return t;
        }

    }

    public T get(int index) {
        IntNode p = sentinel.next;
        if (index < 0 || index > size - 1) {
            return null;
        }
        while(index > 0) {
            p = p.next;
            index-- ;
        }
        return  p.item;
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    public T getRecursive(IntNode node, int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }else if (index == 0) {
            return node.item;
        }else {
            return getRecursive(node.next, index - 1);
        }

    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private IntNode p;

        LinkedListDequeIterator() {
            p = sentinel.next;
        }

        public boolean hasNext() {
            return p == sentinel;
        }

        public T next() {
            T item = p.item;
            p = p.next;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }

        Deque other = (Deque) o;
        if (other.size() != size) {
            return false;
        }
        IntNode p = sentinel.next;
        for(int i = 0; i < size; i++) {
            if (!p.item.equals(other.get(i))) {
                return false;
            }
            p = p.next;
        }
        return true;
    }



}

