package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 练习2.4.33
 * 索引优先队列的实现
 * 使用pq[]来保存索引,使用keys[]来保存元素,使用qp[]来保存pq[]的逆序————qp[i]的值是i在pq[]中的位置
 * 若i不在队列中，则总是令qp[i]=-1
 * Created by huxijie on 16-11-1.
 */
public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int n;      //元素的个数
    private int pq[];   //索引所在的元素序号,如pq[n]=i表示索引i在元素中的第n个
    private int qp[];   //保存pq[]的逆序,如qp[i]=n表示索引i对应的是元素中的第n个
    private Key keys[]; //保存元素。如keys[i]=key表示索引i对应的元素是key

    public IndexMaxPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException();
        }
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i=0;i<=maxN;i++) {
            qp[i] = -1;
        }
    }

    //是否为空
    private boolean isEmpty() {
        return n == 0;
    }

    //是否含有索引i
    public boolean contains(int i) {
        return qp[i] != -1;
    }

    //比较大小
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    //交换
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    //上游
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k/2,k);
            k = k / 2;
        }
    }

    //下沉
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j<n && less(j,j+1)) j++;
            if (!less(k,j)) break;
            exch(k, j);
            k = j;
        }
    }

    //插入
    public void insert(int i, Key key) {
        if (contains(i)) {
            throw new IllegalArgumentException("优先队列中已有该索引");
        }
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    //将索引为i的元素设为key
    public void change(int i, Key key) {
        if (!contains(i)) {
            throw new IllegalArgumentException("优先队列中没有索引i");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    //删去索引i及其相关联的元素
    public void delete(int i) {
        if (!contains(i)) {
            throw new NoSuchElementException("优先队列中没有索引i");
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    //返回最大元素
    public Key max() {
        if (n == 0) {
            throw new NoSuchElementException("队列是空的");
        }
        return keys[pq[1]];
    }

    //返回最大元素的索引
    public int maxIndex() {
        if (n == 0) {
            throw new NoSuchElementException("队列是空的");
        }
        return pq[1];
    }

    //删除最大元素并返回它的索引
    public int delMax() {
        if (n == 0) {
            throw new NoSuchElementException("队列是空的");
        }
        int min = pq[1];
        exch(1, n--);
        sink(1);
        keys[min] = null;
        qp[min] = -1;
        pq[n + 1] = -1;
        return min;
    }

    //返回索引i相关联的元素
    public Key keyOf(int i) {
        if (!contains(i)) {
            throw new NoSuchElementException("优先队列中没有索引i");
        }
        return keys[i];
    }

    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        private IndexMaxPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMaxPQ<Key>(pq.length - 1);
            for (int i=1;i<=n;i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        IndexMaxPQ<String> pq = new IndexMaxPQ<>(strings.length);
        for (int i=0;i<strings.length;i++) {
            pq.insert(i, strings[i]);
        }

        System.out.println("输出所有元素：");
        for (int i : pq) {
            System.out.println(i + " " + strings[i]);
        }

        System.out.println();

        System.out.println("由大到小打印元素：");
        while (!pq.isEmpty()) {
            String key = pq.max();
            int i = pq.delMax();
            System.out.println(i + " " + key);
        }
    }


}
