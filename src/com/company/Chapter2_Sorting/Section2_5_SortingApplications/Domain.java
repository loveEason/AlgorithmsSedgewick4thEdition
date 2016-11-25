package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

/**
 * 2.5.14
 * 逆域名排序
 * 为域名编写一个数据类型并为它实现一个compareTo()方法，使之能够按照逆向的域名排序。
 * Created by huxijie on 16-11-24.
 */

import java.util.Arrays;
import java.util.Scanner;
public class Domain implements Comparable<Domain> {
    private final String[] fields;
    private final int n;

    public Domain(String name) {
        fields = name.split("\\.");
        n = fields.length;
    }

    @Override
    public String toString() {
        if (n == 0) return "";
        String s = fields[0];
        for (int i=1;i<n;i++) {
            s = fields[i] + "." + s;
        }
        return s;
    }

    @Override
    public int compareTo(Domain o) {
        for (int i=0;i<Math.min(this.n,o.n);i++) {
            String s = this.fields[this.n - i - 1];
            String t = o.fields[o.n - i - 1];
            int c = s.compareTo(t);
            if (c < 0) return -1;
            else if (c > 0) return 1;
        }
        return this.n - o.n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入域名个数：");
        int count = Integer.parseInt(scanner.nextLine());
        Domain domains[] = new Domain[count];
        System.out.println("请输入域名：");
        for (int i=0;i<count;i++) {
            domains[i] = new Domain(scanner.nextLine());
        }
        Arrays.sort(domains);
        for (int i=0;i<count;i++) {
            System.out.println(domains[i]);
        }
    }
}
