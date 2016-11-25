package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

import com.company.Chapter2_Sorting.Section2_3_QuickSort.Quick;

import java.util.Scanner;

/**
 * 练习2.5.4
 * 实现一个方法String[] dedup(String[] a),返回一个有序的a[]
 * Created by huxijie on 16-11-6.
 */
public class Dedup {
    private static String[] dedup(String[] a) {
        Quick.sort(a);
        int n = a.length;
        int same = 0;
        for (int i=0;i<n-1;i++) {
            if (a[i].equals(a[i + 1])) {
                same++;
            }
        }
        String[] result = new String[a.length - same];
        int j = 0;
        for (int i=0;i<n-1;i++) {
            if (!a[i].equals(a[i + 1])) {
                result[j++] = a[i];
            }
        }
        result[j++] = a[n - 1];
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        String[] result = dedup(strings);
        for (int i=0;i<result.length;i++) {
            System.out.print(result[i]+" ");
        }
        System.out.println();
    }
}
