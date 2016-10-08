package com.company.Chapter2_Sorting.Section2_3_QuickSort;

import java.util.Scanner;

/**
 * 2.3.22 快速三向切分
 * 用将重复元素放置于子数组两端的方式实现一个信息量最优的排序算法。
 * 使用两个索引p和q，使得a[lo..p-1]和a[q+1..hi]的元素都和a[lo]相等。
 * 使用另外两个索引i和j,使得a[p..i-1]小于a[lo],a[j+1..q]大于a[lo]。
 * 在内循环中加入代码，在a[i]和v相等时将其与a[p]交换（并将p加1），
 * 在a[j]和v相等且a[i]和a[j]尚未和v进行比较之前将其与a[q]交换。
 *
 * Created by huxijie on 16-10-8.
 */
public class QuickX {
    private static final int INSERTION_SORT_CUTOFF = 8;
    private static final int MEDIAN_OF_3_CUTOFF = 40;

    private QuickX() {}

    //比较大小
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //是否相等
    private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }

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

    //插入排序
    private static void insertSort(Comparable[] a, int lo, int hi) {
        for (int i=lo;i<=hi;i++) {
            for (int j=i;j>lo&&less(a[j],a[j-1]);j--) {
                exch(a, j, j - 1);
            }
        }
    }

    //返回三个元素中的中位数所在的下标
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i],a[j]) ?
                (less(a[j],a[k]) ? j : less(a[i],a[k]) ? k : i) :
                (less(a[k],a[j]) ? j : less(a[k],a[i]) ? k : i));
    }

    //外部排序接口
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    //内部排序算法
    private static void sort(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_CUTOFF) {
            insertSort(a, lo, hi);
            return;
        } else if (n <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, lo, lo + n / 2, hi);
            exch(a, lo, m);
        }
        //Tukey's ninther方法
        else {
            int eps = n/8;
            int mid = lo + n / 2;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps);
            int m2 = median3(a, mid - eps, mid, mid + eps);
            int m3 = median3(a, hi - eps - eps, hi - eps, hi);
            int ninther = median3(a, m1, m2, m3);
            exch(a, ninther, lo);
        }
        //快速三向切分
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i],v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v,a[--j])) ;

            if (i == j && eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
            if (eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (eq(a[j], v)) {
                exch(a, --q, j);
            }
        }

        i = j + 1;
        for (int k=lo;k<=p;k++) {
            exch(a, k, j--);
        }
        for (int k=hi;k>=q;k--) {
            exch(a, k, i++);
        }
        sort(a, lo, j);
        sort(a, i, hi);
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
