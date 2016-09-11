package com.company;

/**
 * Created by huxijie on 16-9-4.
 */
public class Steque<Item> {
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int n;

    public boolean isEmpty() {
        return n==0;
    }
    public int size() {
        return n;
    }
    public void push(Item item) {
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {

        }
    }
}
