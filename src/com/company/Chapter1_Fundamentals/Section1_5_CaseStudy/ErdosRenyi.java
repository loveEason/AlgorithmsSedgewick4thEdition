package com.company.Chapter1_Fundamentals.Section1_5_CaseStudy;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 1.5.17
 * 随机连接
 * 从命令行接受一个整数N，在0到N-1之间产生随机整数对，调用connected()判断它们是否相连，如果不是则调用union()方法
 * 不断循环直到所有触点均相互连通并打印出生成的连接总数
 * Created by huxijie on 16-9-11.
 */
public class ErdosRenyi {
    public static int count(int N) {
        int edges = 0;
        UF uf = new UF(N);
        while (uf.count() > 1) {
            int i = StdRandom.uniform(N);
            int j = StdRandom.uniform(N);
            //if (uf.connected(i,j)) continue;
            uf.union(i, j);
            edges++;
        }

        return edges;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        int[] edges = new int[trials];

        //重复实验
        for (int t=0;t<trials;t++) {
            edges[t] = count(n);
        }

        System.out.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
        System.out.println("mean       = " + StdStats.mean(edges));
        System.out.println("Stddev     = " + StdStats.stddev(edges));
    }
}
