package com.company;

/**
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
