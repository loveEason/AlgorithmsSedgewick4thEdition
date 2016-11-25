package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

/**
 * 2.5.24
 * 稳定的优先队列
 * Created by huxijie on 16-11-25.
 */
public class StableMinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private long[] time;
    private int n;
    private long timestamp = 1;

    public StableMinPQ(int initCapicity) {
        pq = (Key[]) new Comparable[initCapicity + 1];
        time = new long[initCapicity + 1];
        n = 0;
    }

    public StableMinPQ() {
        this(1);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key min() {
        if ((isEmpty())) {
            throw new RuntimeException("Priority queue underflow");
        }
        return pq[1];
    }

    private void resize(int capacity) {
        Key[] tempPQ = (Key[]) new Comparable[capacity];
        long[] tempTime = new long[capacity];
        for (int i=1;i<=n;i++) {
            tempPQ[i] = pq[i];
            tempTime[i] = time[i];
        }
        pq = tempPQ;
        time = tempTime;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        int cmp = pq[i].compareTo(pq[j]);
        if (cmp > 0) {
            return true;
        }
        if (cmp < 0) {
            return false;
        }
        return time[i] > time[j];
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        long tempTime = time[i];
        time[i] = time[j];
        time[j] = tempTime;
    }

    private boolean isMinHeap(int k) {
        if (k > n) {
            return true;
        }
        int left = 2 * k, right = 2 * k + 1;
        if (left <= n && greater(k, left)) {
            return false;
        }
        if (right <= n && greater(k, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    public void insert(Key x) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        n++;
        pq[n] = x;
        time[n] = ++timestamp;
        swim(n);
    }

    public Key delMin() {
        if (isEmpty()) {
            throw new RuntimeException("Priority queue underflow");
        }
        exch(1, n);
        Key min = pq[n--];
        sink(1);
        pq[n + 1] = null;
        time[n + 1] = 0;
        if (n > 0 && n == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }
        return min;
    }

    private static final class Tuple implements Comparable<Tuple> {
        private String name;
        private int id;

        private Tuple(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public int compareTo(Tuple o) {
            return this.name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return name + " " + id;
        }
    }

    public static void main(String[] args) {
        StableMinPQ<Tuple> pq = new StableMinPQ<>();

        String text = "it was the best of times it was the worst of times it was the "
                + "age of wisdom it was the age of foolishness it was the epoch "
                + "belief it was the epoch of incredulity it was the season of light "
                + "it was the season of darkness it was the spring of hope it was the "
                + "winter of despair";
        String[] strings = text.split(" ");
        for (int i=0;i<strings.length;i++) {
            pq.insert(new Tuple(strings[i], i));
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.delMin());
        }
        System.out.println();

    }

}
