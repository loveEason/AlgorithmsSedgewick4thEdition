package com.company;

import java.util.Iterator;

/**
 * Created by huxijie on 16-9-4.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node{
        Node before;
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int n;

    public boolean isEmpty() {return n==0;}
    public int size() {return n;}
    public void pushLeft(Item item) {
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldHead = first;
            first = new Node();
            first.item = item;
            first.next = oldHead;
            oldHead.before = first;
            if (n==1) last = oldHead;
        }
        n++;
    }
    public void pushRight(Item item) {
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        }
        else {
            Node oldTail = last;
            last = new Node();
            last.item = item;
            oldTail.next = last;
            last.before = oldTail;
            if (n==1) first = oldTail;
        }
        n++;
    }
    public Item popLeft() {
        if (isEmpty()) throw new RuntimeException("the deque is empty");
        else if (n==1) {
            Node left = first;
            first = last = null;
            n--;
            return left.item;
        }
        else {
            Node left = first;
            first = left.next;
            first.before = null;
            n--;
            return left.item;
        }
    }
    public Item popRight() {
        if (isEmpty()) throw new RuntimeException("the deque is empty");
        else if (n==1) {
            Node right = last;
            last = first = null;
            n--;
            return right.item;
        }
        else {
            Node right = last;
            last = right.before;
            last.next = null;
            n--;
            return right.item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new IteratorDeque();
    }

    private class IteratorDeque implements Iterator<Item> {
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
