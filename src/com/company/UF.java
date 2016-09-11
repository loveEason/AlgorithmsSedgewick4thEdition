package com.company;

import edu.princeton.cs.algs4.StdIn;

import java.util.Scanner;

/**
 * Created by huxijie on 16-9-8.
 */
public class UF {
    private int[] id;   //分量id(以触点作为索引)
    private int count;  //分量数量

    public UF(int N) {
        //初始化分量id数组
        count = N;
        id = new int[count];
        for (int i=0;i<N;i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    //如果p和q存在于同一个分量中则返回true
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    //p(0到N-1)所在的分量的标识符
    public int find(int p) {
//        //quick-find算法
//        return id[p];

        //quick-union算法
        while (p != id[p]) p = id[p];
        return p;
    }

    //在p和q之间添加一条连接
    public void union(int p, int q) {
////        //对应于quick-find算法
//        //将p和q归并到相同的分量中
//        int pID = find(p);
//        int qID = find(q);
//
//        //如果p和q已经在相同的分量之中则不需要采取任何行动
//        if (pID == qID) return;
//        //将p的分量重命名为q的名称
//        for (int i=0;i<id.length;i++) {
//            if (id[i] == pID) id[i] = qID;
//        }
//        count--;

        //对应于quick-union算法
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[pRoot] = qRoot;
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

        UF uf = new UF(N);
        for (int i=1;i<(strings.length-1);i+=2) {
            int p = Integer.parseInt(strings[i]);
            int q = Integer.parseInt(strings[i + 1]);
            if (uf.connected(p,q)) continue;    //如果已经连通则忽略
            uf.union(p,q);  //归并分量
            System.out.println(p + " " + q);    //打印连接
            uf.show();
        }
        System.out.println(uf.count+" components");
    }
}
