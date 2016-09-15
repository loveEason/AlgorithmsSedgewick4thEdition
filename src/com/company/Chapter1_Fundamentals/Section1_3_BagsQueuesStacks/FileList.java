package com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks;

import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Queue;

import java.io.File;

/**
 * 1.3.43
 * 文件列表，使用队列+递归
 * Created by huxijie on 16-9-8.
 */
public class FileList {
    private Queue<String> queue = new Queue<>();

    public FileList(String path) {
        queue.enqueue(path);
    }

    public void show() {
        while (!queue.isEmpty()) {
            String tmp = queue.dequeue();
            File dir = new File(tmp);
            if (dir.isDirectory()) {
                System.out.println("—————"+tmp+"——————");
                File[] files = dir.listFiles();
                for (File f : files) {
                    String fullFilePath = f.getAbsolutePath();
                    queue.enqueue(fullFilePath);
                    show();
                }
            }
            else System.out.println(tmp);
        }
    }
}
