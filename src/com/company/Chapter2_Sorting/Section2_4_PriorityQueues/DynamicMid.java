package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;


import java.util.Scanner;

/**
 * 练习2.4.30
 * 动态中位数的查找
 * 构造两个堆，最大堆的最大元素小于最小堆的最小元素
 * Created by huxijie on 16-10-31.
 */
public class DynamicMid<Key extends Comparable<Key>> {
    MaxPQ<Key> maxPQ;
    MinPQ<Key> minPQ;
    int n;

    public DynamicMid() {
        maxPQ = new MaxPQ<Key>();
        minPQ = new MinPQ<Key>();
        n = 0;
    }


    public void insert(Key key) {
        if (maxPQ.isEmpty()) {
            maxPQ.insert(key);
            return;
        }
        if (key.compareTo(maxPQ.max()) > 0) {
            minPQ.insert(key);
        } else {
            maxPQ.insert(key);
        }
        while (maxPQ.size() > minPQ.size() + 1) {
            minPQ.insert(maxPQ.delMax());
        }
        while (maxPQ.size() < minPQ.size()) {
            maxPQ.insert(minPQ.delMin());
        }
    }

    public int size() {
        n = maxPQ.size() + minPQ.size();
        return n;
    }

    public Key middle() {
        Key key = maxPQ.max();
        return key;
    }

    public Key delMiddle() {
        Key key = maxPQ.delMax();
        while (maxPQ.size() < minPQ.size()) {
            maxPQ.insert(minPQ.delMin());
        }
        return key;
    }

    public void show() {
        minPQ.show();
        maxPQ.show();
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        DynamicMid<String> dynamicMid = new DynamicMid<>();
        for (String s : a) {
            dynamicMid.insert(s);
        }
        System.out.println("所有元素是：");
        dynamicMid.show();
        System.out.print("第一个中位数是：" + dynamicMid.delMiddle()+"\n");
        System.out.println("现在剩余元素是：");
        dynamicMid.show();
        System.out.print("现在的中位数是：" + dynamicMid.delMiddle()+"\n");
    }
}
