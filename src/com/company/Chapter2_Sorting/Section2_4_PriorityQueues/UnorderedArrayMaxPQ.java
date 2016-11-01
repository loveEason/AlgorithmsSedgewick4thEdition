package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

/**
 * 练习2.4.3
 * 用无序数组实现优先队列
 * Created by huxijie on 16-10-27.
 */
public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public UnorderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity+1];
        n = 0;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
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
        pq[++n] = x;
    }

    public Key delMax() {
        int max = 1;
        for (int i=2;i<=n;i++) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, n);
        return pq[n--];
    }

    public static void main(String[] args) {
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            System.out.println(pq.delMax());
    }


}
