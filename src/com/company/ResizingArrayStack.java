package com.company;

import java.util.Iterator;

/**
 * Created by huxijie on 16-9-3.
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[])new Object[1];
    private int n;

    public boolean isEmpty() {return n==0;}
    public int size() {return n;}
    private void resize(int max) {
        Item[] temp = (Item[])new Object[max];
        for(int i=0;i<n;i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void push(Item item) {
        if (n == a.length) resize(a.length*2);
        a[n++] = item;
    }
    public Item pop() {
        Item item = a[--n];
        a[n] = null;
        if (n>0 && n==a.length/4) resize(a.length/2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();

    }
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }
}
