package com.company;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

/**
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
