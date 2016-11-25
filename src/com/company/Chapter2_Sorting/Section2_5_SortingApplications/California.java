package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 2.5.16
 * 公正的选举
 * 将字母按照如下顺序排序，而不是按照字母表的顺序。
 * R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
 * Created by huxijie on 16-11-24.
 */
public class California {
    private static class CandidateComparator implements Comparator<String> {
        private static String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

        @Override
        public int compare(String a, String b) {
            int n = Math.min(a.length(), b.length());
            for (int i=0;i<n;i++) {
                int aindex = order.indexOf(a.charAt(i));
                int bindex = order.indexOf(b.charAt(i));
                if (aindex < bindex) {
                    return -1;
                } else if (aindex > bindex) {
                    return 1;
                }
            }
            return a.length() - b.length();
        }
    }

    public static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入候选人个数：");
        int count = Integer.parseInt(scanner.nextLine());
        String[] candidates = new String[count];
        System.out.println("请输入候选人名字：");
        for (int i=0;i<count;i++) {
            candidates[i] = scanner.nextLine();
        }

        Arrays.sort(candidates, CANDIDATE_ORDER);
        System.out.println("候选人名字排序如下：");
        for (int i=0;i<count;i++) {
            System.out.println(candidates[i]);
        }
    }
}
