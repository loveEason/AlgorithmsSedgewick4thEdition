package com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks;

import java.util.Iterator;

/**
 * 1.3.39  环形缓冲区，又称为环形队列，是一种定长为N的先进先出的数据结构
 * Created by huxijie on 16-9-7.
 */
public class RingBuffer<Item> implements Iterable<Item> {
    private int front;
    private int rear;
    private int n;
    private int tmpFront;
    private Item[] a;

    public RingBuffer(int max) {
        n = max+1;
        a = (Item[])new Object[n];
        front = rear = tmpFront = 0;
    }

    public boolean isEmpty() {
        return front%n == rear%n;
    }

    public boolean isFull() {
        return (rear+1)%n == front%n;
    }

    public void enqueue(Item i) {
        if (isFull()) throw new RuntimeException("the ringBuffer is full");
        rear = (rear+1)%n;
        a[rear] = i;
    }

    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("the ringBuffer is empty");
        front = (front+1)%n;
        Item tmp = a[front];
        a[front] = null;
        tmpFront = front;
        return tmp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<Item> {
        private int judge = 0;
        @Override
        public boolean hasNext() {
            if (judge == 0 && (front+1)%n != (rear+1)%n) return true;
            else if (judge == 1) {
                judge = 0;
                return false;
            }
            else return false;
        }

        @Override
        public Item next() {
            front = (front+1)%n;
            Item tmp = a[front];
            if ((front+1)%n == (rear+1)%n) {
                front = tmpFront;
                judge =1;
            }
            return tmp;
        }

        @Override
        public void remove() {

        }
    }
}
