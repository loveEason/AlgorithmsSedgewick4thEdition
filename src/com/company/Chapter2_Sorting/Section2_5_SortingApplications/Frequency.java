package com.company.Chapter2_Sorting.Section2_5_SortingApplications;

import com.sun.prism.impl.Disposer;

import java.util.*;

/**
 * 练习2.5.8
 * 从标准输入读取一列字符串并按照字符串出现频率由高到低的顺序打印出每个字符串及其出现次数
 * Created by huxijie on 16-11-6.
 */
public class Frequency {
    private class Record{
        String word;
        int freq;

        public Record(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }

        @Override
        public String toString() {
            return word + "    " + Integer.toString(freq);
        }
    }

    public static class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            if (o1.freq < o2.freq) {
                return -1;
            } else if (o1.freq > o2.freq) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    public void frequency(String[] a) {
        int n = a.length;
        Arrays.sort(a);

        List<Record> records = new ArrayList<>(n);
        String word = a[0];
        int freq = 1;
        for (int i=1;i<n;i++) {
            if (!a[i].equals(word)) {
                records.add(new Record(word, freq));
                freq = 0;
                word = a[i];
            }
            freq++;
        }
        records.add(new Record(word, freq));

        Collections.sort(records, new MyComparator());

        int m = records.size();
        for (int i=m-1;i>=0;i--) {
            System.out.println(records.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        String[] strings = read.split(" ");
        Frequency frequency = new Frequency();
        frequency.frequency(strings);
    }

}
