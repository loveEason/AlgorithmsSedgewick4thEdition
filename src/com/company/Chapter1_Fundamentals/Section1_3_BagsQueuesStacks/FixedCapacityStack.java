package com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks;

/**
 * 泛型定容栈
 * Created by huxijie on 16-9-3.
 */
public class FixedCapacityStack<Item> {
    private Item[] a;
    private int n;
    public FixedCapacityStack(int cap) {
        a = (Item[])new Object[cap];
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }
    public void push(Item item) {
        if(n == a.length) resize(2*a.length);
        a[n++] = item;
    }
    public Item pop() {
        Item item = a[--n];
        a[n] = null;
        if(n>0 && n==a.length/4) resize(a.length/2);
        return item;
    }
    private void resize(int max) {
        Item[] temp = (Item[])new Object[max];
        for(int i=0;i<n;i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public boolean isFull() {
        return n == a.length;
    }


}
