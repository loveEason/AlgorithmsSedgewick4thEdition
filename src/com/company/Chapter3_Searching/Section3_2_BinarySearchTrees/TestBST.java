package com.company.Chapter3_Searching.Section3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.In;

/**
 * 3.2.10
 * 编写一个测试用例来测试BST中的API
 * Created by huxijie on 16-11-29.
 */
public class TestBST {
    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int n = keys.length;
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; i < n; i++) {
            st.put(keys[i], i);
        }




        System.out.println("size = " + st.size());
        System.out.println("min  = " + st.min());
        System.out.println("max  = " + st.max());
        System.out.println();


        // print keys in order using allKeys()
        System.out.println("Testing keys()");
        System.out.println("--------------------------------");
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
        System.out.println();

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
        String[] from = { "A", "Z", "X", "O", "B", "C" };
        String[] to   = { "Z", "A", "X", "Z", "G", "L" };
        System.out.println("range search");
        System.out.println("-------------------");
        for (int i = 0; i < from.length; i++) {
//            System.out.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                System.out.print(s + " ");
            System.out.println();
        }
        System.out.println();

        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        System.out.println("After deleting the smallest " + st.size() / 2 + " keys");
        System.out.println("--------------------------------");
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
        System.out.println();

        // delete all the remaining keys
        while (!st.isEmpty()) {
            st.delete(st.select(st.size() / 2));
        }
        System.out.println("After deleting the remaining keys");
        System.out.println("--------------------------------");
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
        System.out.println();

        System.out.println("After adding back the keys");
        System.out.println("--------------------------------");
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
        System.out.println();

    }
}
