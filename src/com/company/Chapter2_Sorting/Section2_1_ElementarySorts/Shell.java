package com.company.Chapter2_Sorting.Section2_1_ElementarySorts;

import java.util.Random;
import java.util.Scanner;

/**
 * 希尔排序
 * Created by huxijie on 16-9-15.
 */
public class Shell {
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

    //输出数组
    public static void show(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    //排序
    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;  //1,4,13,40,121,364,1093...
        }
        while (h >= 1) {
            for (int i=h;i<n;i++) {
                for (int j=i;j>=h&&less(a[j],a[j-h]);j-=h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    //是否已排序
    public static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i=0;i<n;i++) {
            if (less(a[i+1],a[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] a = read.split(" ");

        Integer[] a = new Integer[100];
        Random random = new Random();
        for (int i=0;i<100;i++) {
            a[i] = random.nextInt(100)+1;
        }

        sort(a);
        assert isSorted(a);
        show(a);
    }
}
