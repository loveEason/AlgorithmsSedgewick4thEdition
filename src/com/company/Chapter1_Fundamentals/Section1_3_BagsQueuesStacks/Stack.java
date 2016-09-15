package com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 下压堆栈，链表实现
 * Created by huxijie on 16-9-3.
 */
public class Stack<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node next;
        //普通构造函数
        Node() {}

        //复制构造函数
        Node(Node s) {
            this.item = s.item;
            if(s.next != null) {
                this.next = new Node(s.next);
            }
        }
    }
    private Node first;
    private int n;

    //普通构造函数
    public Stack() {}

    //复制栈功能
    public Stack(Stack oldStack) {
        first = new Node(oldStack.getFirst());
    }

    public Node getFirst() {
        return first;
    }

    public boolean isEmpty() {return first == null;}
    public int size() {return n;}
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }
    private class StackIterator implements Iterator<Item> {
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

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }
}
