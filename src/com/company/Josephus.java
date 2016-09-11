package com.company;

import java.util.Scanner;

/**
 * Created by huxijie on 16-9-7.
 */
public class Josephus {
    public static void main(String[] arts) {
        int m;
        int n;
        Scanner scanner = new Scanner(System.in);
        String read =scanner.nextLine();
        String[] strings = read.split(" ");
        m = Integer.parseInt(strings[1]);
        n = Integer.parseInt(strings[0]);
        Queue<Integer> queue = new Queue<>();
        for (int i=0;i<n;i++) {
            queue.enqueue(i);
        }
        while (!queue.isEmpty()) {
            for (int i=0;i<m-1;i++) {
                queue.enqueue(queue.dequeue());
            }
            System.out.println(queue.dequeue());
        }
    }
}
