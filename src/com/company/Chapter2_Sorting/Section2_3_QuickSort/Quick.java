package com.company.Chapter2_Sorting.Section2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * 快速排序
 * Created by huxijie on 16-10-7.
 */
public class Quick {
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

    //切分:将数组切分为a[lo,i-1],a[i],a[i+1,hi]
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i==hi) break;
            }
            while (less(v, a[--j])) {
                //if (j==lo) break;     //这个边界检查是多余的
            }
            if (i>=j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    //快速排序
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi<=lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    //排序的外部接口
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
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
