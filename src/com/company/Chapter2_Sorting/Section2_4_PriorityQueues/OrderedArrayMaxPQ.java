package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

/**
 * 练习2.4.3
 * 用有序数组实现优先队列
 * Created by huxijie on 16-10-27.
 */
public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity+1];
        n = 0;
    }

    private boolean less(Key i, Key j) {
        return i.compareTo(j) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key x) {
        int i= n;
        while (i >= 1 && less(x, pq[i])) {
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i + 1] = x;
        n++;
    }

    public Key delMax() {
        return pq[n--];
    }

    public static void main(String[] args) {
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            System.out.println(pq.delMax());
    }


}
