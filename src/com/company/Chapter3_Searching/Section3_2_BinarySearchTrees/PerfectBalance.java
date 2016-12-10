package com.company.Chapter3_Searching.Section3_2_BinarySearchTrees;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 3.2.25
 * 完美平衡
 * 用一组键构造一棵和二分查找等价的二叉查找树。
 * Created by huxijie on 16-12-10.
 */
public class PerfectBalance {
    private static void perfect(BST bst, String[] a) {
        Arrays.sort(a);
        System.out.println("after sorted:");
        for (String s : a) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("the sequence of binary search:");
        perfect(bst, a, 0, a.length - 1);
        System.out.println();
    }

    private static void perfect(BST bst, String[] a, int lo, int hi) {
        if (hi < lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        bst.put(a[mid], mid);
        System.out.print(a[mid] + " ");
        perfect(bst, a, lo, mid - 1);
        perfect(bst, a, mid + 1, hi);
    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        Scanner scanner = new Scanner(System.in);
        String strings = scanner.nextLine();
        String[] words = strings.split(" ");
        perfect(bst, words);
    }
}
