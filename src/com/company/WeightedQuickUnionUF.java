package com.company;

import java.util.Scanner;

/**
 * 加权quick-union算法
 * Created by huxijie on 16-9-11.
 */
public class WeightedQuickUnionUF {
    private int[] id;   //父链接数组（由触点索引）
    private int[] sz;   //（由触点索引的）各个根节点所对应的分量的大小
    private int count;  //连通分量的数量

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i=0;i<N;i++) id[i] = i;
        sz = new int[N];
        for (int i=0;i<N;i++) sz[i] = 1;
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p,int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public void show() {
        for (int i=0;i<id.length;i++) {
            System.out.println(i+": "+id[i]);
        }
    }

    public static void main(String[] args) {
        //解决由StdIn得到的动态连通性问题
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        int N = Integer.parseInt(strings[0]);    //读取触点数量

        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(N);
        for (int i=1;i<(strings.length-1);i+=2) {
            int p = Integer.parseInt(strings[i]);
            int q = Integer.parseInt(strings[i + 1]);
            if (weightedQuickUnionUF.connected(p,q)) continue;    //如果已经连通则忽略
            weightedQuickUnionUF.union(p,q);  //归并分量
            System.out.println(p + " " + q);    //打印连接
            weightedQuickUnionUF.show();
        }
        System.out.println(weightedQuickUnionUF.count+" components");
    }
}
