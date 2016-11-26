package com.company.Chapter3_Searching.Section3_1_SymbolTables;

import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Queue;

import java.util.Iterator;

/**
 * 顺序查找（基于无序链表）
 * Created by huxijie on 16-11-26.
 */
public class SequentialSearchST<Key, Value> {
    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    private Node first;
    private int n;

    //查找给定的键，返回相关联的值
    public Value get(Key key) {
        for (Node x=first;x!=null;x=x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    //查找给定的键，找到则更新其值，否则在表中新建结点
    public void put(Key key, Value value) {
        for (Node x=first;x!=null;x=x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        n++;
    }

    //键是否存在于表中
    public boolean contains(Key key) {
        for (Node x=first;x!=null;x=x.next) {
            if (key.equals(x.key)) {
                return true;
            }
        }
        return false;
    }

    //从表中删去键key及其对应的值
    public void delete(Key key) {
        Node preNode = first;
        Node curNode = first;
        while (curNode != null) {
            if (curNode == first && preNode == first && key.equals(curNode.key)) {
                curNode = curNode.next;
                first = curNode;
                n--;
                return;
            } else if (!key.equals(curNode.key)) {
                preNode = curNode;
                curNode = curNode.next;
            } else if (curNode != first && key.equals(curNode.key)) {
                preNode.next = curNode.next;
                curNode.key = null;
                n--;
            }
        }
    }

    //表是否为空
    public boolean isEmpty() {
        if (first == null) {
            return true;
        } else {
            return false;
        }
    }

    //表中的键值对数量
    public int size() {
        return n;
    }

    private class TableIterator implements Iterator<Key> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }


    //表中的所有键的集合
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x=first;x!=null;x=x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }


}
