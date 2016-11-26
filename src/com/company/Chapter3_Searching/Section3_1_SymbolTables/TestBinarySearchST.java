package com.company.Chapter3_Searching.Section3_1_SymbolTables;

import java.util.Scanner;

/**
 * 3.1.29
 * 测试用例
 * Created by huxijie on 16-11-26.
 */
public class TestBinarySearchST {
    public static void main(String[] args) {
        System.out.println("输入字符串:");
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        int n = strings.length;

        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        for (int i=0;i<n;i++) {
            st.put(strings[i], i);
        }

        System.out.println("size = " + st.size());
        System.out.println("min = " + st.min());
        System.out.println("max = " + st.max());

        System.out.println("Testing keys():");
        System.out.println("--------------------------------");
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }


        // print keys in order using select
        System.out.println("Testing select");
        System.out.println("--------------------------------");
        for (int i = 0; i < st.size(); i++)
            System.out.println(i + " " + st.select(i));
        System.out.println();

        // test rank, floor, ceiling
        System.out.println("key rank floor ceil");
        System.out.println("-------------------");
        for (char i = 'A'; i <= 'Z'; i++) {
            String s = i + "";
            System.out.printf("%2s %4d %4s %4s\n", s, st.rank(s), st.floor(s), st.ceiling(s));
        }
        System.out.println();

        // test range search and range count
        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to   = { "Z", "A", "X", "Z", "G", "L" };
        System.out.println("range search");
        System.out.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            System.out.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                System.out.print(s + " ");
            System.out.println();
        }
        System.out.println();
    }
}
