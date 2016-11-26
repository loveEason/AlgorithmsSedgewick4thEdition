package com.company.Chapter3_Searching.Section3_1_SymbolTables;

import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Queue;

import java.util.Scanner;

/**
 * 3.1.2
 * 开发一个符号表的实现，使用无序数组来实现基本的API
 * Created by huxijie on 16-11-26.
 */
public class ArrayST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    public ArrayST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        n = 0;
    }

    public ArrayST() {
        this(1);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void resize(int capacity) {
        Key[] tmpKeys = (Key[]) new Comparable[capacity];
        Value[] tmpValues = (Value[]) new Object[capacity];
        for (int i=0;i<n;i++) {
            tmpKeys[i] = keys[i];
            tmpValues[i] = values[i];
        }

        keys = tmpKeys;
        values = tmpValues;
    }

    public boolean contains(Key key) {
        for (int i=0;i<n;i++) {
            if (key.compareTo(keys[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    public void put(Key key, Value value) {
        if (n == keys.length) {
            resize(keys.length * 2);
        }
        for (int i=0;i<n;i++) {
            if (key.compareTo(keys[i]) == 0) {
                values[i] = value;
                return;
            }
        }
        keys[n] = key;
        values[n] = value;
        n++;
    }

    public Value get(Key key) {
        for (int i=0;i<n;i++) {
            if (key.compareTo(keys[i]) == 0) {
                return values[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        for (int i=0;i<n;i++) {
            if (key.compareTo(keys[i]) == 0) {
                for (int j=i;j<n-1;j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                keys[n - 1] = null;
                values[n - 1] = null;
                n--;
                if (n > 0 && n == keys.length / 4) {
                    resize(keys.length / 2);
                }
                return;
            }
        }
        throw new RuntimeException("the key is not exist");
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i =0;i<n;i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<>();
        Scanner scanner = new Scanner(System.in);
        String read;
        String[] strings;
        System.out.println("输入字符串-整数对:");
        while (scanner.hasNext()) {
            read = scanner.nextLine();
            strings = read.split(" ");
            st.put(strings[0], Integer.parseInt(strings[1]));
        }
        System.out.println("符号表如下：");
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }
}
