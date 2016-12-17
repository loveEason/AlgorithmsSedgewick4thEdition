package com.company.Chapter3_Searching.Section3_5_SearchingApplications;

import com.company.Chapter3_Searching.Section3_4_HashTables.LinearProbingHashST;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 3.5.20
 * 对照索引：每个单词在书中出现的所有位置
 * Created by huxijie on 16-12-17.
 */
public class Concordance {
    public static void main(String[] args) {
        int lineNumber = 0;
        File file = null;
        Scanner scanner = null;
        String fileName = args[0];
        LinearProbingHashST<String, Set<String>> hashST = new LinearProbingHashST<>();

        try {
            file = new File(fileName);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;
                String[] words = line.split(" ");

                for (int i=0;i<words.length;i++) {
                    String s = words[i];
                    if (!hashST.contains(s)) {
                        hashST.put(s, new LinkedHashSet<>());
                    }
                    hashST.get(s).add("第" + lineNumber + "行,第" + (i + 1) + "个");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("store successfully!");
        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String query = scanner.next();
            if (hashST.contains(query)) {
                for (String result : hashST.get(query)) {
                    System.out.println(" " + result);
                }
            } else {
                System.out.println("not exist!");
            }
        }
    }
}
