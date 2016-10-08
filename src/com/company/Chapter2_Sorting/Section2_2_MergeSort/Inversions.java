package com.company.Chapter2_Sorting.Section2_2_MergeSort;

import java.util.Scanner;

/**
 * 2.2.19 倒置
 * 编写一个线性对数级别的算法统计给定数组中的“倒置”数量
 * Created by huxijie on 16-10-5.
 */
public class Inversions {
    //private static Comparable[] aux;    //归并所需的辅助数组
    private int count = 0;

    //比较大小
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //是否已经排序
    private static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i=1;i<n;i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
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

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }


    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        //将数组ea[lo...hi]排序
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    //原地归并的抽象化方法
//    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
//        //将a[lo...mid]和a[mid+1...hi]归并
//        int i = lo, j = mid + 1;
//        for (int k=lo;k<=hi;k++) {  //将a[lo...hi]复制到aux[lo...hi]
//            aux[k] = a[k];
//        }
//        for (int k=lo;k<=hi;k++) {  //归并回到a[lo...hi]
//            if (i>mid) a[k] = aux[j++];   //左半边用尽，取右半边的元素
//            else if (j>hi) a[k] = aux[i++];   //右半边用尽，取左半边的元素
//            else if (less(aux[j],aux[i])) a[k] = aux[j++];    //右半边的当前元素小于左半边的元素，取右半边的元素
//            else a[k] = aux[i++]; //左半边的当前元素小于右半边的元素，取左半边的元素
//        }
//    }

    /**
     * 2.2.10 快速排序
     * 实现一个merge()方法，按降序将a[]的后半部分复制到aux[]，然后将其归并到a[]中。
     * 这样就可以去掉内循环中检测某半边是否用尽的代码。
     * 注意：这样的排序产生的结果是不稳定的。
     */
    private static long merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        long inversions = 0;
        for (int i = lo; i <= mid; i ++) {
            aux[i] = a[i];
        }
        for (int i=mid+1;i<=hi;i++) {
            aux[i] = a[hi - i + mid + 1];
        }
        int i = lo, j = hi;
        for (int k=lo;k<=hi;k++) {
            if (less(aux[j],aux[i])) {
                a[k] = aux[j--];
                inversions += (mid - i + 1);
            }
            else a[k] = aux[i++];
        }
        return inversions;
    }

    private static long count(Comparable[] a, Comparable[] b, Comparable[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi<=lo) return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid + 1, hi);
        inversions += merge(b,aux,lo,mid,hi);
        return inversions;
    }

    public static long count(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        Comparable[] aux = new Comparable[a.length];
        for (int i=0;i<a.length;i++) {
            b[i] = a[i];
        }
        long inversions = count(a, b, aux, 0, a.length - 1);
        return inversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        System.out.println(count(a));
    }
}
