package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

import java.io.File;
import java.util.Arrays;

/**
 * 2.5.28
 * 按文件名排序
 * Created by huxijie on 16-11-25.
 */
public class FileSorter {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        if (!directory.exists()) {
            System.out.println(args[0] + " does not exist");
            return;
        }
        if (!directory.isDirectory()) {
            System.out.println(args[0] + " is not a directory");
            return;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("could not read files");
            return;
        }
        Arrays.sort(files);
        for (int i=0;i<files.length;i++) {
            System.out.println(files[i].getName());
        }
    }
}
