package com.company.Chapter1_Fundamentals.Section1_4_AnalysisOfAlgorithms;

/**
 * 统计一个文件中所有和为0的三整数元组的数量（假设整数不会溢出）
 * Created by huxijie on 16-9-6.
 */
public class ThreeSum {
    public static int count(int[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                for (int k=j+1;k<n;k++) {
                    if (a[i]+a[j]+a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
