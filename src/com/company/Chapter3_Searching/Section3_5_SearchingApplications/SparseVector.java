package com.company.Chapter3_Searching.Section3_5_SearchingApplications;

import com.company.Chapter3_Searching.Section3_4_HashTables.LinearProbingHashST;

/**
 * 稀疏向量
 * Created by huxijie on 16-12-17.
 */
public class SparseVector {
    private int d;          //维度
    private LinearProbingHashST<Integer, Double> hashST;

    public SparseVector(int d) {
        this.d = d;
        this.hashST = new LinearProbingHashST<>();
    }

    //保存这个向量的非零坐标
    public void put(int i, double value) {
        if (i < 0 || i >= d) {
            throw new IndexOutOfBoundsException("Illegal index");
        }
        if (value != 0.0) {
            hashST.put(i, value);
        }
    }

    //得到这个向量的某个坐标值
    public double get(int i) {
        if (i < 0 || i >= d) {
            throw new IndexOutOfBoundsException("Illegal index");
        }
        if (hashST.contains(i)) {
            return hashST.get(i);
        } else {
            return 0.0;
        }
    }

    //得到非零值个数
    public int nnz() {
        return hashST.size();
    }

    //得到向量维度
    public int dimension() {
        return d;
    }

    //得到向有键
    public Iterable<Integer> keys() {
        return hashST.keys();
    }

    //是否包含对应序号项（即该序号项对应的值是否为零）
    public boolean contains(int i) {
        return hashST.contains(i);
    }

    //和同维度向量的点乘
    public double dot(SparseVector that) {
        if (this.dimension() != that.dimension()) {
            throw new IllegalArgumentException("Vector lengths disagree!");
        }

        double sum = 0.0;
        //遍历非零个数最小的向量
        if (this.nnz() <= that.nnz()) {
            for (int i : this.keys()) {
                if (that.contains(i)) {
                    sum += this.get(i) * that.get(i);
                }
            }
        } else {
            for (int i : that.keys()) {
                if (this.contains(i)) {
                    sum += that.get(i) * this.get(i);
                }
            }
        }
        return sum;
    }

    //和给定数组的点乘
    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : this.keys()) {
            sum += this.get(i) * that[i];
        }
        return sum;
    }

    //和同维度向量的加
    public SparseVector plus(SparseVector that) {
        if (this.dimension() != that.dimension()) {
            throw new IllegalArgumentException("Vector lengths disagree");
        }
        SparseVector c = new SparseVector(d);
        for (int i : this.keys()) {
            c.put(i, this.get(i));
        }
        for (int i : that.keys()) {
            c.put(i, c.get(i) + that.get(i));
        }
        return c;
    }

    //得到向量的模
    public double norm() {
        return Math.sqrt(this.dot(this));
    }

    //字符串表示
    @Override
    public String toString() {
        String represent = "[ ";
        for (int i=0;i<d;i++) {
            if (hashST.contains(i)) {
                represent = represent + hashST.get(i) + "    ";
            } else {
                represent = represent + "0    ";
            }
        }
        represent += "]";

        return represent;
    }

    public static void main(String[] args) {
        SparseVector a = new SparseVector(5);
        SparseVector b = new SparseVector(5);
        a.put(2, 0.36);
        a.put(3, 0.36);
        a.put(4, 0.18);
        b.put(0, 0.05);
        b.put(1, 0.04);
        b.put(2, 0.36);
        b.put(3, 0.37);
        b.put(4, 0.19);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a dot b = " + a.dot(b));
        System.out.println("a + b = " + a.plus(b));

    }
}
