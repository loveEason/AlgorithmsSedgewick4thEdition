package com.company.Chapter3_Searching.Section3_5_SearchingApplications;

import com.company.Chapter3_Searching.Section3_4_HashTables.LinearProbingHashST;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 索引（以及反向索引）的查找
 * Created by huxijie on 16-12-17.
 */
public class LookupIndex {
    public static void main(String[] args) {
        File file = new File(args[0]);
        String sp = args[1];                        //索引分隔符
        LinearProbingHashST<String, Queue<String>> hashST = new LinearProbingHashST<>();
        LinearProbingHashST<String, Queue<String>> hashTS = new LinearProbingHashST<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(sp);
                String key = tokens[0];
                for (int i=1;i<tokens.length;i++) {
                    String value = tokens[i];
                    if (!hashST.contains(key)) {
                        hashST.put(key,new LinkedList<>());
                    }
                    if (!hashTS.contains(value)) {
                        hashTS.put(value, new LinkedList<>());
                    }
                    hashST.get(key).add(value);
                    hashTS.get(value).add(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String query = scanner.nextLine();
            if (hashST.contains(query)) {
                for (String s : hashST.get(query)) {
                    System.out.println(" " + s);
                }
            } else if (hashTS.contains(query)) {
                for (String s : hashTS.get(query)) {
                    System.out.println(" " + s);
                }
            } else {
                System.out.println("not exist!");
            }
        }
    }
}
