package com.company.Chapter3_Searching.Section3_5_SearchingApplications;

import com.company.Chapter3_Searching.Section3_4_HashTables.LinearProbingHashST;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 字典的查找
 * Created by huxijie on 16-12-17.
 */
public class LookupCSV {
    public static void main(String[] args) {
        File file = new File(args[0]);
        int keyField = Integer.parseInt(args[1]);   //表示文件中每一行键所在的位置
        int valField = Integer.parseInt(args[2]);   //表示文件中每一行值所在的位置
        LinearProbingHashST<String, String> hashST = new LinearProbingHashST<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String key = tokens[keyField];
                String value = tokens[valField];
                hashST.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String query = scanner.nextLine();
            if (hashST.contains(query)) {
                System.out.println(hashST.get(query));
            } else {
                System.out.println("not exist!");
            }
        }
    }
}
