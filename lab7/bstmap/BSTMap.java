package bstmap;

import net.sf.saxon.functions.Compare;

import java.security.Key;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{
    private BSTNode root;

    public class BSTNode {
        private K key;//sorted by key
        private V value;// associated data
        private BSTNode left, right;//subtrees
        private int size; // number of BSTNodes in the subtree

        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            size = 0;
        }
    }

    public BSTMap() {}

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) { //if a map (key, null), contains(key) return true, get(key) return null
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(root, key) != null;
    }

    @Override
    public V get(K key) {
        BSTNode res = get(root, key);
        return  res == null ? null : res.value;
    }

    public BSTNode get(BSTNode x, K key) {// if the key exist, return the node.Otherwise return null
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (root == null) {
            return  null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x;
        }

    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size + 1;
    }

    @Override
    public void put(K key, V value) {
        root  = put(root, key, value);
    }

    public BSTNode put(BSTNode x, K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument to put() is null");
        }

        if (x == null) {
            return new BSTNode(key, value, null, null);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        }
        if (cmp > 0) {
            x.right = put(x.right, key, value);
        }

        x.size += 1;
        return x;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
