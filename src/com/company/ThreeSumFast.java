package com.company;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

/**
 * Created by huxijie on 16-9-6.
 */
public class ThreeSumFast {
    public static int count(int[] a){
        int n = a.length;
        int cnt = 0;
        Arrays.sort(a);
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (BinarySearch.rank(-(a[i]+a[j]),a) > j) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
