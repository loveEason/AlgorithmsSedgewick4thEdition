package com.company.Chapter3_Searching.Section3_4_HashTables;

import com.company.Chapter3_Searching.Section3_1_SymbolTables.SequentialSearchST;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于拉链法的散列表
 * Created by huxijie on 16-12-15.
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int N;      //键值对总数
    private int M;      //散列表的大小
    private SequentialSearchST<Key,Value>[] st;     //存放链表对象的数组

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST[]) new SequentialSearchST[M];
        for (int i=0;i<M;i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff % M);
    }

    private void resize(int newM) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<Key, Value>(newM);
        for (int i=0;i<M;i++) {
            if (!st[i].isEmpty()) {
                for (Key k : st[i].keys()) {
                    t.put(k, st[i].get(k));
                }
            }
        }
        st = t.st;
        M = t.M;
        N = t.N;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        if (N >= M*10) {
            resize(2 * M);
        }
        if (st[hash(key)].isEmpty()) {
            N++;
        }
        st[hash(key)].put(key, value);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        int i = hash(key);
        if (st[i].contains(key)) {
            N--;
        }
        st[i].delete(key);

        if (M > INIT_CAPACITY && N <= 2 * M) {
            resize(M / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i=0;i<M;i++) {
            for (Key key : st[i].keys()) {
                queue.add(key);
            }
        }
        return queue;
    }
}
