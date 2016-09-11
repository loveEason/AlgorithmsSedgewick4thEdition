package com.company;

/**
 * Created by huxijie on 16-9-3.
 */
public class ResizingArrayQueueOfStrings {
    private int n;
    private int first;
    private int last;
    private String[] queue;

    public ResizingArrayQueueOfStrings() {
        queue = new String[2];
        n = 0;
        first = 0;
        last = 0;
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }
    public void resize(int max) {
        String[] tmp = new String[max];
        for(int i=0;i<n;i++) {
            tmp[i] = queue[(first+i)%queue.length];
        }
        queue = tmp;
        first = 0;
        last = n;
    }
    public void enqueue(String s) {
        if (n == queue.length) resize(queue.length*2);
        queue[last++] = s;
        if (last == queue.length) last = 0;
        n++;
    }
    public String dequeue() {
        String result = queue[first];
        queue[first] = null;
        n--;
        first++;
        if (first == queue.length) first = 0;
        if (n>0 && n==queue.length/4) resize(queue.length/2);
        return result;
    }
}
