package com.company.Chapter2_Sorting.Section2_2_MergeSort;

import java.util.Scanner;

/**
 * 2.2.16 自然的归并排序
 * 编写一个自底向上的归并排序，当需要将子数组排序时能够利用数组中已经有序的部分。
 * 首先找到一个有序的子数组（移动指针直到当前元素比上一个元素小为止），然后再找到另一个并将它们归并。
 * Created by huxijie on 16-10-4.
 */
public class MergeNature {
    private static int[] subArray; //存放每个已经有序的子数组的第一个元素在原数组的下标位置

    //比较大小
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
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

    //求出数组中已经有序的子数组个数+1
    private static int sumOfSortedSubArray(Comparable[] a) {
        int n = a.length;
        int sum = 0;
        subArray = new int[n];
        subArray[sum++] = 0;
        Comparable bigger = a[0];

        for (int i=1;i<n;i++) {
            if (a[i].compareTo(bigger)<0) {
                subArray[sum++] = i;
                bigger = a[i];
            }
            else {
                bigger = a[i];
            }
        }
        subArray[sum++] = n-1;  //存储最后一个元素的下标，便于后面操作

        return sum;
    }

    //对子数组两两归并排序
    private static void natureMerge(Comparable[] a, int lo, int hi, int mid) {
        if (hi == mid ) hi = mid+1;      //若hi==mid，说明最后一个分数组的元素个数为1，也就是原数组中最后一个元素。
        else if(hi == -1){               //若hi==-1,说明只剩下最后一个分数组，直接返回
            return;
        }
        int tmpN = hi-lo+1;
        Comparable[] aux = new Comparable[tmpN];
        int i = lo, j = mid+1;
        for (int k=0;k<tmpN;k++) {
            if (i>mid) aux[k] = a[j++];
            else if (j>hi) aux[k] = a[i++];
            else if (less(a[i],a[j])) aux[k] = a[i++];
            else aux[k] = a[j++];
        }
        i=lo;
        for (int k=0;k<tmpN;k++) {
            a[lo++] = aux[k];
        }
    }

    //自然的归并排序接口
    public static void sort(Comparable[] a) {
        int n = a.length;
        int sum = sumOfSortedSubArray(a);
        while (sum != 2) {  //sum==2则说明原数组只有一个有序的子数组，也就是说整个数组已经有序
            for (int i=0;i<sum-1;i+=2) {

                natureMerge(a,subArray[i],subArray[i+2]-1,subArray[i+1]-1);
            }
            sum = sumOfSortedSubArray(a);
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
