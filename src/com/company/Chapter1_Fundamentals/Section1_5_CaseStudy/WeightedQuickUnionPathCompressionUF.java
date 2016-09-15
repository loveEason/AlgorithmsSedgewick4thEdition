package com.company.Chapter1_Fundamentals.Section1_5_CaseStudy;

import java.util.Scanner;

/**
 * 1.5.13
 * 路径压缩的加权quick-union算法
 * Created by huxijie on 16-9-11.
 */
public class WeightedQuickUnionPathCompressionUF {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionPathCompressionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i= 0;i<N;i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public int find(int p) {
        validate(p);
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
        validate(p);
        validate(q);
        int pRoot = find(p);
        int qRoot = find(q);

        if (connected(p, q)) {
            return;
        }
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else {
            id[qRoot] = id[pRoot];
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        //解决由StdIn得到的动态连通性问题
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        int N = Integer.parseInt(strings[0]);    //读取触点数量

        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(N);
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
