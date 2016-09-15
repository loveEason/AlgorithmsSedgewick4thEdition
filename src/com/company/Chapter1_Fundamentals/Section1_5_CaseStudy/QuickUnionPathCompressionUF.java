package com.company.Chapter1_Fundamentals.Section1_5_CaseStudy;

import java.util.Scanner;

/**
 * 1.5.12 路径压缩的quick-union算法
 * 在find()方法中添加一个循环来将从p到根节点的路径上的每个触点都连接到根节点
 * Created by huxijie on 16-9-11.
 */
public class QuickUnionPathCompressionUF {
    private int[] id;
    private int count;

    public QuickUnionPathCompressionUF(int N) {
        count = N;
        id = new int[N];
        for (int i=0;i<N;i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        while (p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        //解决由StdIn得到的动态连通性问题
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        int N = Integer.parseInt(strings[0]);    //读取触点数量

        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(N);
        for (int i=1;i<(strings.length-1);i+=2) {
            int p = Integer.parseInt(strings[i]);
            int q = Integer.parseInt(strings[i + 1]);
            if (uf.connected(p,q)) continue;    //如果已经连通则忽略
            uf.union(p,q);  //归并分量
            System.out.println(p + " " + q);    //打印连接
        }
        System.out.println(uf.count+" components");
    }
}
