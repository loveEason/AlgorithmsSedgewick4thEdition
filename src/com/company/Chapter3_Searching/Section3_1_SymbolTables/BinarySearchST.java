package com.company.Chapter3_Searching.Section3_1_SymbolTables;

import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Queue;

/**
 * 基于二分查找的有序符号表
 * Created by huxijie on 16-11-26.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        n = 0;
    }

    public BinarySearchST() {
        this(1);
    }

    public int size() {
        return n;
    }

    public int size(Key lo, Key hi) {
        int i = rank(lo);
        int j = rank(hi);
        return j - i;
    }

    private void resize(int capicity) {
        Key[] tmpKeys = (Key[]) new Comparable[capicity];
        Value[] tmpValues = (Value[]) new Object[capicity];
        for (int i=0;i<n;i++) {
            tmpKeys[i] = keys[i];
            tmpValues[i] = values[i];
        }
        keys = tmpKeys;
        values = tmpValues;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    //计算小于给定键的键的数量
    public int rank(Key key) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    public void put(Key key, Value value) {
        if (n == keys.length) {
            resize(keys.length * 2);
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        } else {
            for (int j=n;j>i;j--) {
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
            keys[i] = key;
            values[i] = value;
            n++;
        }
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[n - 1];
    }

    //排名为k的键
    public Key select(int k) {
        return keys[k];
    }

    //小于等于key的最大键
    public Key floor(Key key) {
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        } else if (i == 0) {
            return null;
        } else {
            return keys[i - 1];
        }
    }

    //大于等于key的最小键
    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    //键key是否存在于表中
    public boolean contains(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void delete(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            while (i < n - 1) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
                i++;
            }
            keys[n - 1] = null;
            values[n - 1] = null;
            n--;
            if (n > 0 && n <= keys.length / 4) {
                resize(keys.length / 2);
            }
        } else {
            System.out.println("the key is not exist");
        }

    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        int j = rank(hi);
        for (int i=rank(lo);i<j;i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    public Iterable<Key> keys() {
        return keys(keys[0], keys[n - 1]);
    }

}
