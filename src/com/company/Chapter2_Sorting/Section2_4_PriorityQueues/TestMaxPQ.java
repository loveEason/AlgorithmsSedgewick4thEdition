package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

import java.util.Scanner;

/**
 * 有用到优先队列的练习题
 * Created by huxijie on 16-10-27.
 */
public class TestMaxPQ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] a = read.split(" ");
        MaxPQ<String> maxPQ = new MaxPQ<>(a.length);

        /*
         * 练习2.4.1
         * 用序列P R I O * R * * I * T * Y * * * Q U E * * * U * E(字母表示插入元素，星号表示删除最大元素)
         * 操作一个初始为空的优先队列。给出每次删除最大元素返回的字符。
         */
//        for (String s : a) {
//            if (s.compareTo("*") == 0) {
//                System.out.print(maxPQ.delMax() + " ");
//            } else {
//                maxPQ.insert(s);
//            }
//        }

        /*
         * 练习2.4.5
         * 将E A S Y Q U E S T I O N 顺序插入一个面向最大元素的堆中，给出结果
         */
//        for (String s : a) {
//            maxPQ.insert(s);
//        }
//        maxPQ.show();

        /*
         * 练习2.4.6
         * 按照练习2.4.1的规则，        for (String s : a) {
            if (s.compareTo("*") == 0) {
                maxPQ.delMax();
                maxPQ.show();
            } else {
                maxPQ.insert(s);
                maxPQ.show();
            }
        }
         * 用序列 P R I O * R * * I * T * Y * * * Q U E * * * U * E操作一个初始为空的面向最大元素的堆，
         * 给出每次操作后堆的内容。
         */
//        for (String s : a) {
//            if (s.compareTo("*") == 0) {
//                maxPQ.delMax();
//                maxPQ.show();
//            } else {
//                maxPQ.insert(s);
//                maxPQ.show();
//            }
//        }

        /*
         * 练习2.4.18
         * 在MaxPQ中，如果一个用例使用insert()插入一个比队列中的所有元素都大的新元素，随后立即调用delMax()。
         * 假设没有重复元素，此时的堆和进行这些操作之前的堆完全相同吗？
         * 进行两次insert()(第一次插入一个比队列所有元素都大的元素，第二次插入一个更大的元素)操作接两次delMax()操作呢？
         */
//        for (String s : a) {
//            maxPQ.insert(s);
//        }
//        System.out.println("第一次：");
//        maxPQ.show();
//        System.out.println("输入一个更大的元素：");
//        read = scanner.nextLine();
//        maxPQ.insert(read);
//        maxPQ.delMax();
//        maxPQ.show();
//        System.out.println("连续输入两个更大的元素：");
//        read = scanner.nextLine();
//        a = read.split(" ");
//        for (String s : a) {
//            maxPQ.insert(s);
//        }
//        maxPQ.delMax();
//        maxPQ.delMax();
//        maxPQ.show();

        /*
         * 练习2.4.19
         * 实现MaxPQ的一个构造函数，接受一个数组作为参数。
         */
//        maxPQ = new MaxPQ<String>(a);
//        maxPQ.show();


        /*
         * 练习2.4.27 找出最小元素
         * 在MinPQ中加入一个min()方法。
         */
        for (String s : a) {
            maxPQ.insert(s);
        }
        System.out.println(maxPQ.min());

    }
}
