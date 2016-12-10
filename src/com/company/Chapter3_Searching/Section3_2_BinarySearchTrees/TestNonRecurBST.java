package com.company.Chapter3_Searching.Section3_2_BinarySearchTrees;

/**
 * 对非递归二叉树的测试
 * Created by huxijie on 16-11-29.
 */
public class TestNonRecurBST {
    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int n = keys.length;
        NonrecursiveBST<String, Integer> st = new NonrecursiveBST<>();
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
        for (int i = 1; i <= st.size(); i++)
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

    }
}
