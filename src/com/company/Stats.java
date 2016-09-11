package com.company;

import edu.princeton.cs.algs4.Bag;

import java.util.Scanner;

/**
 * Created by huxijie on 16-9-3.
 */
public class Stats {
    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<Double>();
        System.out.println("input some data:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("")) break;
            else numbers.add(Double.parseDouble(s));
        }
        int n = numbers.size();
        double sum = 0.0;
        for(double x:numbers) {
            sum+=x;
        }
        double mean = sum/n;
        sum = 0.0;
        for(double x:numbers) {
            sum += (x-mean)*(x-mean);
        }
        double std = Math.sqrt(sum/(n-1));

        System.out.printf("Mean:%.2f\n",mean);
        System.out.printf("Std dev:%.2f\n",std);
    }
}
