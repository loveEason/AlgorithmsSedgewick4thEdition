package com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks;

/**
 * 1.3.31
 * 双向链表
 * Created by huxijie on 16-9-4.
 */
public class Double_Linked_List<Item> {
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
    public void addInHead(Item item) {
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
            if (n == 1) last = oldHead;
        }
        n++;
    }
    public void addInTail(Item item) {
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
            if (n == 1) first = oldTail;
        }
        n++;
    }
    public void deleteHead() {
        if (isEmpty()) throw new RuntimeException("the double-linked-list is empty");
        else if (n==1) first = last = null;
        else {
            first.next.before = null;
            first = first.next;
        }
        n--;
    }
    public void deleteTail() {
        if (isEmpty()) throw new RuntimeException("the double-linked-list is empty");
        else if (n==1) first = last = null;
        else {
            last.before.next = null;
            last = last.before;
        }
        n--;
    }
}
