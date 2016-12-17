package com.company.Chapter3_Searching.Section3_4_HashTables;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于线性探测的符号表
 * Created by huxijie on 16-12-15.
 */
public class LinearProbingHashST<Key, Value> {
    private int N;  //符号表中键值对的总数
    private int M;  //线性探测表的大小
    private Key[] keys;     //键
    private Value[] values; //值

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int M) {
        this.M = M;
        N = 0;
        keys = (Key[])new Object[M];
        values = (Value[]) new Object[M];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff % M);
    }

    private void resize(int newM) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(newM);
        for (int i=0;i<M;i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) {
            resize(2 * M);
        }

        int i;
        for (i=hash(key);keys[i] != null;i=(i+1)%M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key) {
        int i;
        for (i=hash(key);keys[i]!=null;i=(i+1)%M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        for (int i=hash(key);keys[i]!=null;i=(i+1)%M) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {       //将簇中被删除键的右侧所有键重新反躬插入散列表
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i=0;i<M;i++) {
            if (keys[i] != null) {
                queue.add(keys[i]);
            }
        }
        return queue;
    }


}
