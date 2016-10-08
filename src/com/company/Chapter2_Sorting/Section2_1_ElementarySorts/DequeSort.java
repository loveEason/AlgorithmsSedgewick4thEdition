package com.company.Chapter2_Sorting.Section2_1_ElementarySorts;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2.1.14
 * 出列排序
 * 思路：
 * 第一步：比较第一张牌和第二张牌，让较小的牌在最上面，然后将顶上的牌放到牌底，经过length-1次这样的操作之后，第一张牌一定是最大的，
 *        然后将它放到牌底。
 * 第二步：第一步已经找出最大的牌并且放到了牌底，接着重复第一步，经过length-2次操作之后，第一张牌一定是除了最大的那张牌之外最大的，
 *        同时因为经过了length-2次牌顶移到牌底，最大的那张牌刚好移动到了第二张。所以将顶上的两张牌依次放到牌底。
 * 重复以上两步
 * Created by huxijie on 16-9-15.
 */
public class DequeSort {
    public static boolean bigger(int[] a,int v, int w) {
        if (a[v] > a[w]) {
            return true;
        }
        else return false;
    }

    public static void exch(int[] a,int v,int w) {
        int tmp = a[v];
        a[v] = a[w];
        a[w] = tmp;
    }

    public static void moveQueue(int[] a,int times) {
        int n = a.length;
        for (int i=0;i<times;i++) {
            int tmp = a[0];
            for (int j=0;j<n-1;j++) {
                a[j] = a[j + 1];
            }
            a[n - 1] = tmp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");

        int n = strings.length;
        int[] array = new int[n];
        int i = 0;
        for (String s : strings) {
            array[i] = Integer.parseInt(s);
            i++;
        }

        for (int j = 1; j < n; j++) {
            for (int k = 0; k < n - j; k++) {
                if (bigger(array, 0, 1)) {
                    exch(array, 0, 1);
                }
                moveQueue(array,1);
            }
            moveQueue(array,j);
        }
        for (int m : array) {
            System.out.print(m+" ");
        }
    }
}
