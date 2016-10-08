package com.company.Chapter2_Sorting.Section2_2_MergeSort;

import java.util.Scanner;

/**
 * 2.2.11 改进
 * 实现对归并排序的三项改进：加快小数组的排序速度，检测数组是否已经有序以及通过在递归中交换参数来避免数组复制
 * Created by huxijie on 16-10-4.
 */
public class MergeX {
    private static final int CUTOFF = 7;    //判断是否要采取插入排序的分界线

    //比较大小
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //是否已经排序
    private static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            if (!less(a[i],a[i+1])) return false;
        }
        return true;
    }

    //打印数组
    public static void show(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    //交换位置
    private static void exch(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    //插入排序
    private static void insertSort(Comparable[] a, int lo, int hi) {
        for (int i=lo;i<=hi;i++) {
            for (int j=i;j>lo && less(a[j],a[j-1]);j--) {
                exch(a, j, j - 1);
            }
        }
    }

    //归并排序
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k=lo;k<=hi;k++) {
            if (i>mid) dst[k] = src[j++];
            else if (j>hi) dst[k] = src[i++];
            else if (less(src[j],src[i])) dst[k] = src[j++];
            else dst[k] = src[i++];
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertSort(dst,lo,hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        if (less(src[mid], src[mid + 1])) {
            return;
        }
        merge(src, dst, lo, mid, hi);
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
