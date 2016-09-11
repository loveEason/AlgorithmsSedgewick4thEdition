package com.company;

import java.util.Iterator;

/**
 * 前移编码策略，即假设最近访问过的元素可能会再次访问，
 * 因此可以用于缓存、数据压缩等许多场景。
 * Created by huxijie on 16-9-8.
 */
public class MoveToFront implements Iterable {
    private class Node {
        String item;
        Node next;
    }
    private Node first;
    private int n;

    public boolean isEmpty() {
        return n==0;
    }

    public int size() {
        return n;
    }

    //找到已经存在的并且删除
    private void hasSame_N_Move(String item) {
        Node current = first;
        Node currentBefore =first;

        if (current.item.equals(item)) {
            first = first.next;
            n--;
        }
        currentBefore = current;
        current = current.next;

        for (int i=1;i<n;i++) {
            if (current.item.equals(item)) {
                currentBefore.next = current.next;
                n--;
            }
            currentBefore = current;
            current = current.next;
        }
    }

    public void insert(String item) {
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            first = newNode;
            first.next = null;
        } else {
            hasSame_N_Move(item);
            if (isEmpty()) {
                first = newNode;
                first.next = null;
            } else {
                newNode.next = first;
                first = newNode;
            }
        }
        n++;
    }

    @Override
    public Iterator iterator() {
        return new tmpIterator();
    }

    //用于遍历的迭代器
    private class tmpIterator implements Iterator {
        Node curent = first;

        @Override
        public boolean hasNext() {
            return curent != null;
        }

        @Override
        public Object next() {
            String item = curent.item;
            curent = curent.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }
}
