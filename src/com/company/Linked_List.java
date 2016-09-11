package com.company;

/**
 * Created by huxijie on 16-9-4.
 */
public class Linked_List<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int n;

    public boolean isEmpty() {return n==0;}
    public int size() {return n;}
    public void show() {
        if (isEmpty()) throw new RuntimeException("the linked-list is empty");
        Node current = first;
        while (current != last) {
            System.out.println(current.item);
            current = current.next;
        }
        System.out.println(current.item);
    }
    public void add(Item item) {
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            first.next = null;
            last = first;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            oldLast.next = last;
        }
        n++;
    }
    public void deleteTail() {
        Node current = first;
        if (isEmpty()) throw new RuntimeException("the linked-list is empty");
        else if (n==1) {
            first = last = null;
        }
        else {
            while (current.next != last) {
                current = current.next;
            }
            last = null;
            current.next = null;
            last = current;
        }
        n--;
    }
    public void delete(int k) {
        if (k>n) throw new RuntimeException("the linked-list doesn't have "+k+" items");
        else if (k==1) {
            first = first.next;
            if (first == null) last = first;
        }
        else {
            Node current = first;
            for (int i=2;i<k;i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) last = current;
        }
        n--;
    }
    public boolean find(Item key) {
        if (isEmpty()) throw new RuntimeException("the linked-list is empty");
        Node current = first;
        while (current != null) {
            if (current.item == key) return true;
            current = current.next;
        }
        return false;
    }
    public void remove(Item key) {
        if (isEmpty()) throw new RuntimeException("the linked-list is empty");
        Node current = first;
        Node currentBefore = first;
        while (current != null) {
            if (current.item == key && current == first) {
                first = first.next;
                current = first;
                if (first == null) last = first;
                n--;
            }
            else if (current.item == key && current!=first) {
                currentBefore.next = current.next;
                if (current==last) last = currentBefore;
                n--;
            }
            currentBefore = current;
            current = current.next;
        }
    }

}
