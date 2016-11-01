package com.company.Chapter2_Sorting.Section2_4_PriorityQueues;

import java.util.Comparator;

/**
 * 练习2.4.25 计算数论
 * 在不使用额外空间的条件下，按大小顺序打印所有a^3+b^3的结果，其中a和b为0至N之间的整数。
 * 也就是说，不要全部计算N^2个和然后排序，而是创建一个最小优先队列，初始状态为
 * （0^3+0^3,0,0）,(1^3+1^3,1,1),(2^3+2^3,2,2),...,(N^3+N^3,N,N)。
 * 这样只要优先队列非空，删除并打印最小的元素(i^3+j^3,i,j)。然后如果j<N,插入元素(i^3+(j+1)^,i,j+1)。
 * Created by huxijie on 16-10-30.
 */
public class CubeSum implements Comparable<CubeSum> {
    private final int sum;
    private final int i;
    private final int j;

    public CubeSum(int i, int j) {
        this.sum = i * i * i + j * j * j;
        this.i = i;
        this.j = j;
    }

    @Override
    public int compareTo(CubeSum that) {
        if (this.sum<that.sum) return -1;
        if (this.sum>that.sum) return +1;
        return 0;
    }

    @Override
    public String toString() {
        return sum + " = " + i + "^3" + " + " + j + "^3";
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        MinPQ<CubeSum> pq = new MinPQ<CubeSum>();
        for (int i=0;i<=n;i++) {
            pq.insert(new CubeSum(i, i));
        }

        while (!pq.isEmpty()) {
            CubeSum s = pq.delMin();
            System.out.println(s);
            if (s.j < n) {
                pq.insert(new CubeSum(s.i, s.j + 1));
            }
        }
    }
}
