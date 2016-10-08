package com.company.Chapter2_Sorting.Section2_1_ElementarySorts;

import edu.princeton.cs.algs4.In;

/**
 * 插入排序
 * Created by huxijie on 16-9-13.
 */
public class Insertion {

    //比较两个对象
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //交换两个位置
    public static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    //判断是否已排序
    public static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i=1;i<n;i++) {
            if (less(a[i], a[i - 1])) {
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

    //排序
    public static void sort(Comparable[] a) {
//        int n = a.length;
//        for (int i=1;i<n;i++) {
//            for (int j=i;j>0 && less(a[j],a[j-1]);j--) {
//                exch(a,j,j-1);
//            }
//        }

        /*
         * 哨兵
         * 先找出最小的元素并将其置于数组的最左边，
         * 这样就能去掉内循环的条件j>0
         */
        int n = a.length;
        int exchanges = 0;
        for (int i=n-1;i>0;i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges == 0) {
            return;
        }

        for (int i=2;i<n;i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
