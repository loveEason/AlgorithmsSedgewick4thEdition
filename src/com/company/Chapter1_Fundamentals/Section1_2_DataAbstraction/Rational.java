package com.company.Chapter1_Fundamentals.Section1_2_DataAbstraction;

/**
 * 有理数
 * Created by huxijie on 16-9-2.
 */
public class Rational {
    private long num;
    private long den;

    public Rational(int numerator,int denominator) {
        if(denominator == 0) {
            throw new RuntimeException("Denominator is zero");
        }
        long g = gcd(numerator,denominator);
        num = numerator/g;
        den = denominator/g;
    }

    private long gcd(int p,int q) {
        if (q==0) return p;
        int t = p%q;
        return gcd(q,t);
    }
}
