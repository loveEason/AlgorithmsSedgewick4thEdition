package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

import java.util.*;

/**
 * 练习2.5.12
 * 调度。从标准输入中读取任务的名称和所需的运行时间，根据最短处理时间优先的原则打印出一份调度计划，使得任务完成的平均时间最小。
 * Created by huxijie on 16-11-6.
 */
public class SPT {
    public class Job implements Comparable<Job> {
        private String name;
        private double time;

        public Job(String name, double time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public double getTime() {
            return time;
        }

        @Override
        public int compareTo(Job o) {
            if (this.getTime() < o.getTime()) {
                return -1;
            } else if (this.getTime() > o.getTime()) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return this.getName() + "需用时: " + this.getTime();
        }
    }

    public void deal(String[] strings) {
        int n = strings.length / 2;
        Job[] jobs = new Job[n];
        int j = 0;
        for (int i=0;i<strings.length-1;i+=2) {
            jobs[j++] = new Job(strings[i], Double.parseDouble(strings[i + 1]));
        }
        Arrays.sort(jobs);
        for (j=0;j<n;j++) {
            System.out.println(jobs[j]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        SPT spt = new SPT();
        spt.deal(strings);


    }
}
