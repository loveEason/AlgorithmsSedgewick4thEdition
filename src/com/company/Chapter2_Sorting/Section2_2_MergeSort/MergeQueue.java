package com.company.Chapter2_Sorting.Section2_2_MergeSort;


import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Queue;

/**
 * 2.2.14 归并有序的队列
 * 编写一个静态方法，将两个有序的队列作为参数，返回一个归并后的有序队列
 * Created by huxijie on 16-10-4.
 */
public class MergeQueue {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static Queue merge(Queue v,Queue w) {
        int v_n = v.size();
        int w_n = w.size();
        Queue result = new Queue();
        for (int k=0;k<v_n+w_n;k++) {
            if (v.isEmpty()) result.enqueue(w.dequeue());
            else if (w.isEmpty()) result.enqueue(v.dequeue());
            else {
                Object i = v.head();
                Object j = w.head();
                if (less((Comparable) i, (Comparable) j)) {
                    result.enqueue(i);
                    v.dequeue();
                } else {
                    result.enqueue(j);
                    w.dequeue();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Queue<String> v = new Queue<>();
        Queue<String> w = new Queue<>();
        v.enqueue("h");
        v.enqueue("i");
        v.enqueue("j");
        v.enqueue("k");
        w.enqueue("a");
        w.enqueue("b");
        w.enqueue("c");
        w.enqueue("d");
        w.enqueue("e");
        Queue<String> result = merge(v, w);
        for (String i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
