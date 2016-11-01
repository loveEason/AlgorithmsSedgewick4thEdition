package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

import java.util.Scanner;

/**
 * 堆排序
 * 数组array[0]也有值的
 * Created by huxijie on 16-10-26.
 */
public class HeapSort {
    private static void exch(Comparable[] array, int i, int j) {
        Comparable tmp = array[i-1];
        array[i-1] = array[j-1];
        array[j-1] = tmp;
    }

    private static boolean less(Comparable[] array, int i, int j) {
        return array[i-1].compareTo(array[j-1]) < 0;
    }
    
    private static void sink(Comparable[] array, int k, int n) {
        while (2 * k < n) {
            int j = 2 * k;
            if (j<n && less(array,j,j+1)) j++;
            if (!less(array,k,j)) break;
            exch(array, k, j);
            k = j;
        }
    }

    public static void sort(Comparable[] array) {
        int n = array.length;
        for (int k = n/2;k>=1;k--) {
            sink(array, k, n);
        }
        while (n > 1) {
            exch(array, 1, n--);
            sink(array, 1, n);
        }
    }

    public static void show(Comparable[] array) {
        int n = array.length;
        for (int i=0;i<n;i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        sort(a);
        show(a);
    }
}
