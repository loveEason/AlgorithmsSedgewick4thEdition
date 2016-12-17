package com.company.Chapter3_Searching.Section3_5_SearchingApplications;

/**
 * n阶稀疏矩阵
 * Created by huxijie on 16-12-17.
 */
public class SparseMatrix {
    private int n;      //阶数
    private SparseVector[] rows;    //每一行都是一个稀疏向量

    public SparseMatrix(int n) {
        this.n = n;
        rows = new SparseVector[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new SparseVector(n);
        }
    }

    //存入非零A[i][j]
    public void put(int i, int j, double value) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Illegal index");
        }

        rows[i].put(j, value);
    }

    //得到A[i][j]
    public double get(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Illegal index");
        }

        return rows[i].get(j);
    }

    //得到非零个数
    public int nnz() {
        int sum = 0;
        for (int i=0;i<n;i++) {
            sum += rows[i].nnz();
        }
        return sum;
    }

    //矩阵和向量的乘法
    public SparseVector times(SparseVector x) {
        if (n != x.dimension()) {
            throw new IllegalArgumentException("Dimensions disagree");
        }
        SparseVector b = new SparseVector(n);
        for (int i=0;i<n;i++) {
            b.put(i, rows[i].dot(x));
        }

        return b;
    }

    //矩阵相加
    public SparseMatrix plus(SparseMatrix that) {
        if (this.n != that.n) {
            throw new IllegalArgumentException("Dimensions disagree");
        }
        SparseMatrix result = new SparseMatrix(n);
        for (int i=0;i<n;i++) {
            result.rows[i] = this.rows[i].plus(that.rows[i]);
        }

        return result;
    }

    //字符串表示
    @Override
    public String toString() {
        String represent = "n = " + n + ",nonzeros = " + nnz() + "\n";
        for (int i=0;i<n;i++) {
            represent += i + ": " + rows[i] + "\n";
        }

        return represent;
    }

    public static void main(String[] args) {
        SparseMatrix A = new SparseMatrix(5);
        SparseVector x = new SparseVector(5);
        A.put(0, 0, 1.0);
        A.put(1, 1, 1.0);
        A.put(2, 2, 1.0);
        A.put(3, 3, 1.0);
        A.put(4, 4, 1.0);
        A.put(2, 4, 0.3);
        x.put(0, 0.75);
        x.put(2, 0.11);
        System.out.println("x     : " + x);
        System.out.println("A     : " + A);
        System.out.println("Ax    : " + A.times(x));
        System.out.println("A + A : " + A.plus(A));
    }
}
