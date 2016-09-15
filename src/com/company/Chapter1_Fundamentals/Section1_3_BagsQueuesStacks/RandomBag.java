package com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * 1.3.34  随机背包
 * Created by huxijie on 16-9-6.
 */
public class RandomBag<Item> implements Iterable<Item> {
    private int n;
    private Item[] a = (Item[])new Object[2];

    public boolean isEmpty() {
        return n==0;
    }
    public int size() {
        return n;
    }
    private void resize(int max) {
        Item[] tmp = (Item[])new Object[max];
        for (int i=0;i<n;i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }
    public void add(Item item) {
        if (n==a.length) {
                resize(n*2);
            }
        a[n++] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item> {
        private int i=0;
        private Item[] tmp = a;

        public BagIterator() {
            Collections.shuffle(Arrays.asList(tmp));
        }

        @Override
        public boolean hasNext() {
            return i<n;
        }

        @Override
        public Item next() {
            return tmp[i++];
        }

        @Override
        public void remove() {

        }
    }
}
