package com.company.Chapter3_Searching.Section3_5_SearchingApplications;

import com.company.Chapter3_Searching.Section3_4_HashTables.LinearProbingHashST;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 文件索引
 * Created by huxijie on 16-12-17.
 */
public class FileIndex {
    public static void main(String[] args) {
        LinearProbingHashST<String, Set<File>> hashST = new LinearProbingHashST<>();
        Scanner scanner = null;
        File file = null;
        for (String fileName : args) {
            try {
                file = new File(fileName);
                scanner = new Scanner(file);

                while (scanner.hasNext()) {
                    String word = scanner.next();
                    if (!hashST.contains(word)) {
                        hashST.put(word, new LinkedHashSet<>());
                    }
                    hashST.get(word).add(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String queryWord = scanner.next();
            if (hashST.contains(queryWord)) {
                for (File f : hashST.get(queryWord)) {
                    System.out.println(" " + f.getName());
                }
            } else {
                System.out.println("not exist");
            }
        }
    }
}
