package com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks;

import java.util.Iterator;

/**
 * 先进先出队列
 * Created by huxijie on 16-9-3.
 */
public class Queue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int n;

    //普通构造函数
    public Queue() {}
    //复制队列功能
    public Queue(Queue q) {
        int tmpN = q.size();
        for (int i=0;i<tmpN;i++) {
            Item tmpItem = (Item)q.dequeue();
            this.enqueue(tmpItem);
            q.enqueue(tmpItem);
        }
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return n;
    }
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }
}
