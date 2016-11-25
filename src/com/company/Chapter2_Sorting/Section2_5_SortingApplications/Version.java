package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 练习2.5.10
 * 创建一个数据类型Version来表示软件的版本，为它实现Comparable接口
 * 比如115.1.1、115.10.1、115.10.2
 * 则115.1.1的版本低于115.10.1
 * Created by huxijie on 16-11-6.
 */
public class Version implements Comparable<Version> {
    private String versionNo;

    public Version(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionNo() {
        return versionNo;
    }

    @Override
    public int compareTo(Version o) {
        String[] version_first = this.getVersionNo().split("\\.");
        String[] version_second = o.getVersionNo().split("\\.");
        int judge = version_first[0].compareTo(version_second[0]);
        if (judge < 0) {
            return -1;
        } else if (judge > 0) {
            return 1;
        } else {
            judge = version_first[1].compareTo(version_second[1]);
            if (judge < 0) {
                return -1;
            } else if (judge > 0) {
                return 1;
            } else {
                judge = version_first[2].compareTo(version_second[2]);
                if (judge < 0) {
                    return -1;
                } else if (judge > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        return versionNo;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        Version[] versions = new Version[strings.length];
        for (int i=0;i<strings.length;i++) {
            versions[i] = new Version(strings[i]);
        }

        Arrays.sort(versions);
        for (int i=0;i<versions.length;i++) {
            System.out.println(versions[i]);
        }
    }
}
