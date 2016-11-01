package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

import java.util.Comparator;

/**
 * 基于堆的优先队列
 * Created by huxijie on 16-10-25.
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;       //基于堆的完全二叉树
    private int n = 0;      //存储于pq[1..n]中，pq[0]没有使用
    private Key min;

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    //由下至上的堆有序化，上浮
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    //由上至下的堆有序化，下沉
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j<n && less(j,j+1)) j++;
            if (!less(k,j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i=1;i<=n;i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
        min = null;
    }

    public MaxPQ(Key[] oldPQ) {
        pq = (Key[]) new Comparable[oldPQ.length+1];
        for (int i=1;i<=oldPQ.length;i++) {
            pq[++n] = oldPQ[i - 1];
            swim(n);
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key v) {
        if (n == pq.length - 1) {
            resize(pq.length * 2);
        }
        if (n == 0) {
            min = v;
        } else {
            min = min.compareTo(v) < 0 ? min : v;
        }
        pq[++n] = v;
        swim(n);
    }

    public Key delMax() {
        Key max = pq[1];    //从根结点得到最大元素
        exch(1, n--);       //将其和最后一个结点交换
        pq[n+1] = null;     //防止对象游离
        sink(1);
        if ((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return max;
    }

    public Key max() {
        return pq[1];
    }

    public Key min() {
        return min;
    }

    public void show() {
        for (int i=1;i<=n;i++) {
            System.out.print(pq[i]+" ");
        }
    }

}
