package com.company.Chapter3_Searching.Section3_1_SymbolTables;

import java.util.Scanner;

/**
 * 3.1.1
 * 创建一张符号表并建立字母成绩和数值分数的对应关系。从标准输入读取一系列字母成绩，计算并打印GPA（字母成绩对应的分数的平均值）
 * Created by huxijie on 16-11-26.
 */
public class GPA {
    public static void main(String[] args) {
        SequentialSearchST<String, Double> grades = new SequentialSearchST<>();
        grades.put("A+", 4.33);
        grades.put("A", 4.00);
        grades.put("A-", 3.67);
        grades.put("B+", 3.33);
        grades.put("B", 3.00);
        grades.put("B-", 2.67);
        grades.put("C+", 2.33);
        grades.put("C", 2.00);
        grades.put("C-", 1.67);
        grades.put("D", 1.00);
        grades.put("F", 0.00);

        int n = 0;
        double total = 0.0;
        String grade;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入成绩:");
        while (scanner.hasNext()) {
            grade = scanner.nextLine();
            total += grades.get(grade);
            n++;
        }
        System.out.println("GPA= " + total / n);
    }
}
