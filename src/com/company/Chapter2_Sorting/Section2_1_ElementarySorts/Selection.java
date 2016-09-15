package com.company.Chapter2_Sorting.Section2_1_ElementarySorts;

import edu.princeton.cs.algs4.In;

import java.util.Scanner;

/**
 * 选择排序
 * Created by huxijie on 16-9-13.
 */
public class Selection {
    //比较两个对象
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //交换位置
    public static void exch(Comparable[] v, int i, int j) {
        Comparable tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;
    }

    //输出数组
    public static void show(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    //测试数组元素是否有序
    public static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i=1;i<n;i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    //排序
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            int min = i;
            for (int j=i;j<n;j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a,i,min);
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
