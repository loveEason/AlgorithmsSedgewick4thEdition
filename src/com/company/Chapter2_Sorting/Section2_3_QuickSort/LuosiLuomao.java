package com.company.Chapter2_Sorting.Section2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * 2.3.15 螺丝和螺帽
 * 假设有N个螺丝和N个螺帽混在一堆，你需要快速将它们配对。
 * 一个螺丝只匹配一个螺帽，一个螺帽也只会匹配一个螺丝。
 * 你可以试着把一个螺丝和一个螺帽拧在一起看看谁大了，但不能直接比较两个螺丝或者两个螺帽。
 * Created by huxijie on 16-10-8.
 */
public class LuosiLuomao {
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
    private static int partition(Comparable[] a, int lo, int hi, Comparable[] b, int other) {
//        int i = lo-1, j = hi + 1;
//        Comparable v = b[other];
//        while (true) {
//            while (a[++i].compareTo(v) < 0) {
//                if (i == hi) break;
//            }
//            while (a[--j].compareTo(v) > 0) {
//                if (j == lo) break;
//            }
//            if (i >= j) break;
//            exch(a, i, j);
//        }
//        return j;

        int lt = lo, gt = hi, i = lo;
        Comparable v = b[other];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp<0) exch(a, lt++, i++);
            else if (cmp>0) exch(a, i, gt--);
            else i++;
        }
        return lt;
    }

    //将螺丝和螺帽进行排序
    private static void sort(Comparable[] luosi, int lo1, int hi1, Comparable[] luomao, int lo2, int hi2) {
        if (hi1<=lo1 || hi2<=lo2) return;
        //拿一个螺帽与所有螺丝比较，将螺丝分成三组,返回分组后与螺帽匹配的螺丝的位置
        int i = partition(luosi, lo1, hi1, luomao, lo2);
        //拿已经匹配的螺丝与所有螺帽比较，将螺帽分成三组，返回分组后与螺丝匹配的螺帽的位置
        int j = partition(luomao, lo2, hi2, luosi, i);
        //分别对较小堆和较大堆进行排序
        sort(luosi, lo1, i - 1, luomao, lo2, j - 1);
        sort(luosi,i+1,hi1,luomao,j+1,hi2);
    }

    //解决问题的接口
    public static void solve(Comparable[] luosi, Comparable[] luomao) {
        sort(luosi, 0, luosi.length - 1, luomao, 0, luomao.length - 1);
    }


    public static void main(String[] args) {
        System.out.println("请输入螺丝和螺帽的数量：");
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        int n = Integer.parseInt(read);
        Integer[] luosi = new Integer[n];
        Integer[] luomao = new Integer[n];
        for (int i=0;i<n;i++) {
            luosi[i] = i;
            luomao[i] = i;
        }
        StdRandom.shuffle(luosi);
        StdRandom.shuffle(luomao);
        solve(luosi, luomao);
        System.out.println("配对后的结果:");
        show(luosi);
        show(luomao);
    }
}
