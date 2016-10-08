package com.company.Chapter2_Sorting.Section2_2_MergeSort;

import java.util.Scanner;

/**
 * 自底向上的归并排序
 * Created by huxijie on 16-9-17.
 */
public class MergeBU {
    private static Comparable[] aux;    //归并所需的辅助数组

    //比较大小
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //是否已经排序
    private static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i=1;i<n;i++) {
            if (less(a[i],a[i-1])) return false;
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

    //排序
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        int n = a.length;
        for (int sz=1;sz<n;sz += sz) {  //sz子数组大小
            for (int lo=0;lo<n-sz;lo+=sz+sz) {  //lo子数组索引
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }

    }

    //原地归并的抽象化方法
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo...mid]和a[mid+1...hi]归并
        int i = lo, j = mid + 1;
        for (int k=lo;k<=hi;k++) {  //将a[lo...hi]复制到aux[lo...hi]
            aux[k] = a[k];
        }
        for (int k=lo;k<=hi;k++) {  //归并回到a[lo...hi]
            if (i>mid) a[k] = aux[j++];   //左半边用尽，取右半边的元素
            else if (j>hi) a[k] = aux[i++];   //右半边用尽，取左半边的元素
            else if (less(aux[j],aux[i])) a[k] = aux[j++];    //右半边的当前元素小于左半边的元素，取右半边的元素
            else a[k] = aux[i++]; //左半边的当前元素小于右半边的元素，取左半边的元素
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        int n = a.length;
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
