package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

import java.util.NoSuchElementException;

/**
 * 练习2.4.24
 * 使用链接的优先队列
 * Created by huxijie on 16-10-30.
 */
public class MaxPQ_Link<Key extends Comparable<Key>> {
//    private class Node {
//        Key item;
//        int num;
//        Node parent, lchild, rchild;
//    }
//
//    private int n;
//    private Node froHead;
//    private Node newNode;
//    private Node lastNode;
//
//    public MaxPQ_Link() {
//        n = 0;
//        froHead = new Node();
//        froHead.parent = null;
//        froHead.lchild = null;
//        froHead.rchild = null;
//        froHead.num = 0;
//        lastNode = froHead;
//    }
//
//    private boolean less(Node first, Node second) {
//        return first.item.compareTo(second.item) < 0;
//    }
//
//    private boolean isEmpty() {
//        return n == 0;
//    }
//
//    private void exch(Node first, Node second) {
//        Node tmp1 = first.parent;
//        Node tmp2 = first.lchild;
//        Node tmp3 = first.rchild;
//        first.parent = second.parent;
//        first.lchild = second.lchild;
//        first.rchild = second.rchild;
//        second.parent = tmp1;
//        second.lchild = tmp2;
//        second.rchild = tmp3;
//    }
//
//    private int size() {
//        return n;
//    }
//
//    private void swim(Node source) {
//        while (source.parent != froHead) {
//            if (less(source.parent, source)) {
//                exch(source.parent, source);
//            }
//        }
//    }
//
//    private void sink(Node source) {
//        while (source.lchild != null) {
//            if (source.rchild != null && less(source.lchild, source.rchild)) {
//                exch(source, source.rchild);
//            }
//        }
//    }
//
//    public void insert(Key key) {
//        newNode = new Node();
//        newNode.item = key;
//        newNode.lchild = null;
//        newNode.rchild = null;
//        newNode.num = ++n;
//
//    }
//
//    private Node findByNum(int num) {
//        if (isEmpty()) {
//            throw new NoSuchElementException("maxPQ underflow");
//        }
//        Node current = froHead.rchild;
//        Node current_parent = current;
//        for (int i=2;i<=n;i++) {
//            if (i % 2 == 1) {
//                current = current_parent.rchild;
//            } else {
//                current = current_parent.lchild;
//            }
//            if (current.num == num) {
//                return current;
//            } else {
//                if (i % 2 == 0) {
//
//                } else {
//
//                }
//            }
//        }
//    }
}
