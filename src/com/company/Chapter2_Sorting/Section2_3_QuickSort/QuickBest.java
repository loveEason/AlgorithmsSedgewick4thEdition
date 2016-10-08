package com.company.Chapter2_Sorting.Section2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;

/**
 * 这题暂时看不懂
 * 2.3.16 最佳情况
 * 编写一段程序来生成使算法2.5中的sort()方法表现最佳的数组：
 * 数组大小为N且不包含重复元素，每次切分后两个子数组的大小最多差1
 * Created by huxijie on 16-10-8.
 */
public class QuickBest {
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void best(int[] a, int lo, int hi) {
        for (int i=lo;i<=hi;i++) {
            assert a[i] == i;
        }
        if (hi<=lo) return;
        int mid = lo + (hi - lo) / 2;
        best(a, lo, mid - 1);
        best(a, mid + 1, hi);
        exch(a,lo,mid);
    }

    public static int[] best(int n) {
        int[] a = new int[n];
        for (int i=0;i<n;i++) {
            a[i] = i;
        }
        best(a, 0, n - 1);
        return a;
    }

    public static void main(String[] args) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int n = Integer.parseInt(args[0]);
        int[] a = best(n);
        for (int i = 0; i < n; i++)
            // StdOut.println(a[i]);
            StdOut.print(alphabet.charAt(a[i]));
        StdOut.println();
    }
}
