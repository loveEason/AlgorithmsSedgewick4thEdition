package com.company.Chapter2_Sorting.Section2_1_ElementarySorts;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
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
//            draw((String[]) a,i,i,min);
            exch(a,i,min);
        }
    }

    private static void draw(String[] a, int row, int ith, int min) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(-2.50, row, ith + "");
        StdDraw.text(-1.25, row, min + "");
        for (int i=0;i<a.length;i++) {
            if (i == min) {
                StdDraw.setPenColor(StdDraw.BOOK_RED);
            } else if (i < ith) {
                StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            } else {
                StdDraw.setPenColor(StdDraw.BLACK);
            }
            StdDraw.text(i, row, a[i] + "");
        }

    }

    //display header
    private static void header(String[] a) {
        int n = a.length;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(n / 2.0, -3, "a[ ]");
        for (int i = 0;i<n;i++) {
            StdDraw.text(i, -2, i + " ");
        }
        StdDraw.text(-2.50, -2, "i");
        StdDraw.text(-1.25, -2, "min");
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(-3, -1.65, n - 0.5, -1.65);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i=0;i<a.length;i++) {
            StdDraw.text(i, -1, a[i]);
        }
    }

    //display footer
    private static void footer(String[] a) {
        int n = a.length;
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i=0;i<n;i++) {
            StdDraw.text(i, n, a[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        int n = a.length;
//        sort(a);
//        assert isSorted(a);
//        show(a);

        //可视轨迹
        StdDraw.setCanvasSize(30 * (n + 3), 30 * (n + 3));
        StdDraw.setXscale(-3, n + 1);
        StdDraw.setYscale(n + 1, -3);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 13));

        //draw the header
        header(a);
        //sort the array
        sort(a);
        //draw the footer
        footer(a);
    }
}
