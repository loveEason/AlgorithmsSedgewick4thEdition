package com.company.Chapter2_Sorting.Section2_3_QuickSort;

import java.util.Scanner;
import java.util.Stack;

/**
 * 2.3.20 非递归的快速排序
 * 实现一个非递归的快速排序，使用一个循环来将弹出栈的子数组切分并将结果子数组重新压入栈
 * Created by huxijie on 16-10-8.
 */
public class QuickNoRecursion {
    //比较大小
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //交换位置
    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    //判断是否有序
    private static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            if (a[i].compareTo(a[i+1])>0) return false;
        }
        return true;
    }

    //打印数组
    public static void show(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    //切分
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i],v)) {
                if (i==hi) break;
            }
            while (less(v, a[--j])) ;
            if (i>=j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    //非递归的快速排序
    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        Stack<Integer> st = new Stack();
        if (lo < hi) {
            int mid = partition(a, lo, hi);
            if (lo < mid - 1) {
                st.push(lo);
                st.push(mid - 1);
            }
            if (mid + 1 < hi) {
                st.push(mid + 1);
                st.push(hi);
            }
            //其实就是用栈保存每一个待排序子串的首尾元素下标，下一次while循环时取出这个范围，对这段子序列进行partition操作
            while (!st.empty()) {
                int p = st.pop();
                int q = st.pop();
                mid = partition(a, q, p);
                if (q < mid - 1) {
                    st.push(q);
                    st.push(mid - 1);
                }
                if (mid + 1 < p) {
                    st.push(mid + 1);
                    st.push(p);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
