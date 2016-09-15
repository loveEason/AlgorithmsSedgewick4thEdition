package com.company.Chapter1_Fundamentals.Section1_4_AnalysisOfAlgorithms;

/**
 * 找出一个输入文件中所有和为0的整数对的数量
 * Created by huxijie on 16-9-6.
 */
public class TwoSum {
    public static int count(int[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                if (a[i]+a[j] == 0) cnt++;
            }
        }
        return cnt;
    }
}
