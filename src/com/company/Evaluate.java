package com.company;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

import java.util.Scanner;

/**
 * Dijkstra的双栈算术表达式求值算法
 * Created by huxijie on 16-9-3.
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> val = new Stack<Double>();
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] str = read.split(" ");
        for (String s : str) {

            if (s.equals("("));
            else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = val.pop();
                switch (op) {
                    case "+":
                        v = val.pop()+v;
                        break;
                    case "-":
                        v = val.pop()-v;
                        break;
                    case "*":
                        v = val.pop()*v;
                        break;
                    case "/":
                        v = val.pop()/v;
                        break;
                }
                val.push(v);
            }
            else val.push(Double.parseDouble(s));
        }
        System.out.println("the result is:"+val.pop());
    }
}
