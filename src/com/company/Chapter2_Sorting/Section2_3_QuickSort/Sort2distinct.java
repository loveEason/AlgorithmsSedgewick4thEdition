package com.company.Chapter2_Sorting.Section2_3_QuickSort;

/**
 * 2.3.5 给出一段代码将已知只有两种主键值的数组排序
 * Created by huxijie on 16-10-8.
 */
public class Sort2distinct {
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

    public static void sort(Comparable[] a) {
        int lt = 0, gt = a.length - 1;
        int i = lt+1;
        while (i <= gt) {
            int cmp = a[i].compareTo(a[lt]);
            if (cmp<0) exch(a, lt++, i++);
            else if (cmp>0) exch(a, i, gt--);
            else i++;
        }
    }

    public static void main(String[] args) {
        String s = args[0];
        int n= s.length();
        String[] a = new String[n];
        for (int i=0;i<n;i++) {
            a[i] = s.substring(i, i + 1);
        }
        sort(a);
        show(a);
    }
}
