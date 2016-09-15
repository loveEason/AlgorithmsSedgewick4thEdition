package com.company.Chapter1_Fundamentals.Section1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

/**
 * 用归并排序和二分查找在线性对数级别解决2-sum问题
 * 改进后的算法思想是当且仅当-a[i]存在于数组中（且a[i]非零）时,a[i]存在于某个和为0的整数对中
 * Created by huxijie on 16-9-6.
 */
public class TwoSumFast {
    public static int count(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int cnt = 0;
        for (int i=0;i<n;i++) {
            if (BinarySearch.rank(-a[i],a) > i) {
                cnt++;
            }
        }
        return cnt;
    }
}
