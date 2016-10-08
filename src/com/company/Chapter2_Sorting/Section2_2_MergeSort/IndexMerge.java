package com.company.Chapter2_Sorting.Section2_2_MergeSort;

import java.util.Scanner;

/**
 * 2.2.20 间接排序
 * 编写一个不改变数组的归并排序，它返回一个int[]数组perm,其中perm[i]的值是原数组中第i小的元素的位置
 * Created by huxijie on 16-10-5.
 */
public class IndexMerge {
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

    private static void merge(Comparable[] a, int[] perm, int[] aux, int lo, int mid,int hi) {
        for (int k=lo;k<=hi;k++) {
            aux[k] = perm[k];
        }
        int i = lo, j = mid + 1;
        for (int k=lo;k<=hi;k++) {
            if (i>mid) perm[k] = aux[j++];
            else if (j>hi) perm[k] = aux[i++];
            else if (less(a[aux[j]],a[aux[i]])) perm[k] = aux[j++];
            else perm[k] = aux[i++];
        }
    }

    private static void sort(Comparable[] a, int[] perm, int[] aux, int lo, int hi) {
        if (hi<=lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, perm, aux, lo, mid);
        sort(a, perm, aux, mid + 1, hi);
        merge(a, perm, aux, lo, mid, hi);
    }

    public static int[] sort(Comparable[] a) {
        int n = a.length;
        int[] perm = new int[n];
        for (int i=0;i<n;i++) {
            perm[i] = i;
        }
        int[] aux = new int[n];
        sort(a, perm, aux, 0, n - 1);
        return perm;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        int[] perm = sort(a);
        System.out.print("a[]:" );
        show(a);
        System.out.println();
        System.out.print("perm[]:");
        for (int i : perm) {
            System.out.print(i+" ");
        }
        System.out.println();

    }
}
