package com.company.Chapter2_Sorting.Section2_1_ElementarySorts;

import java.util.*;

/**
 * 扑克demo
 * Created by huxijie on 16-9-15.
 */
public class PokerDemo {
    //存储每一张牌
    private HashMap<Integer,String> pokers = new HashMap<>();
    //存储牌对应的编号
    private ArrayList<Integer> array = new ArrayList<>();

    //构造函数，初始化一副扑克牌
    public PokerDemo() {
        //创建花色数组和点数数组
        String[] colors = {"♠", "♥", "♣", "♦"};
        String[] numbers = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q",
                "K", "A", "2"};
        //从0开始往pokers里存储牌，同时往array里存储编号
        int index = 0;
        for (String color : colors) {
            for (String number : numbers) {
                String poker = color.concat(number);
                pokers.put(index, poker);
                array.add(index);
                index++;
            }
        }
        pokers.put(index, "小王");
        array.add(index);
        index++;
        pokers.put(index, "大王");
        array.add(index);
    }

    //洗牌,洗的是编号
    public void shuffile() {
        Collections.shuffle(array);
    }

    //发斗地主牌，发的也是编号
    public void dealPoker() {
        //为了保证编号是排序的，创建TreeSet集合接收
//        TreeSet<Integer> player1 = new TreeSet<>();
//        TreeSet<Integer> player2 = new TreeSet<>();
//        TreeSet<Integer> player3 = new TreeSet<>();
//        TreeSet<Integer> dipai = new TreeSet<>();

        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();
        ArrayList<Integer> player3 = new ArrayList<>();
        ArrayList<Integer> dipai = new ArrayList<>();


        for (int x=0;x<array.size();x++) {
            if (x >= array.size() - 3) {
                dipai.add(array.get(x));
            } else if (x % 3 == 0) {
                player1.add(array.get(x));
            } else if (x % 3 == 1) {
                player2.add(array.get(x));
            } else if (x % 3 == 2) {
                player3.add(array.get(x));
            }
        }

        //看所有人的牌
        suitSort(player1);
        lookPoker("player1", player1, pokers);
        rankSort(player2);
        lookPoker("player2", player2, pokers);
        lookPoker("player3", player3, pokers);
    }


    //单独看牌
    public void lookPoker(String name, ArrayList<Integer> al, HashMap<Integer, String> hashMap) {
        System.out.println(name+"的牌是： ");
        for (Integer key : al) {
            String value = hashMap.get(key);
            System.out.print(value+" ");
        }
        System.out.println();
    }

    //得到牌的分值，供花色排序用
    public int getPokerValueBySuit(Integer key) {
        String poker = pokers.get(key);
        if (poker == null) {
            return 0;
        } else if (poker.equals("小王")) {
            return 1000;
        } else if (poker.equals("大王")) {
            return 2000;
        } else {
            int suitValue = 0;
            char color = poker.charAt(0);
            switch (color) {
                case '♠':
                    suitValue = 400;
                    break;
                case '♥':
                    suitValue = 300;
                    break;
                case '♣':
                    suitValue = 200;
                    break;
                case '♦':
                    suitValue = 100;
                    break;
            }
            String number = poker.substring(1);
            int numberValue = 0;
            switch (number) {
                case "2":
                    numberValue = 15;
                    break;
                case "A":
                    numberValue = 14;
                    break;
                case "K":
                    numberValue = 13;
                    break;
                case "Q":
                    numberValue = 12;
                    break;
                case "J":
                    numberValue = 11;
                    break;
                default:
                    numberValue = Integer.parseInt(number);
                    break;
            }
            return suitValue+numberValue;
        }

    }

    //得到牌的分值，供数字排序用
    public int getPokerValueByRank(Integer key) {
        String poker = pokers.get(key);
        if (poker == null) {
            return 0;
        } else if (poker.equals("小王")) {
            return 1000;
        } else if (poker.equals("大王")) {
            return 2000;
        } else {
            char color = poker.charAt(0);
            String number = poker.substring(1);
            int numberValue = 0;
            int suitValue = 0;
            switch (number) {
                case "2":
                    numberValue = 15;
                    break;
                case "A":
                    numberValue = 14;
                    break;
                case "K":
                    numberValue = 13;
                    break;
                case "Q":
                    numberValue = 12;
                    break;
                case "J":
                    numberValue = 11;
                    break;
                default:
                    numberValue = Integer.parseInt(number);
                    break;
            }
            numberValue = numberValue*10;
            switch (color) {
                case '♠':
                    suitValue = 4;
                    break;
                case '♥':
                    suitValue = 3;
                    break;
                case '♣':
                    suitValue = 2;
                    break;
                case '♦':
                    suitValue = 1;
                    break;
            }
            return numberValue+suitValue;
        }
    }


    //根据花色排序
    public void suitSort(ArrayList<Integer> arrayList) {
        arrayList.sort(new suitCompator());
    }

    //根据牌面数字排序使用
    public void rankSort(ArrayList<Integer> arrayList) {
        arrayList.sort(new rankCompator());
    }


    public static void main(String[] args) {
        PokerDemo pokerDemo = new PokerDemo();
        pokerDemo.shuffile();
        pokerDemo.dealPoker();
    }

    //比较器，用于花色排序
    private class suitCompator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            int valueA = getPokerValueBySuit(o1);
            int valueB = getPokerValueBySuit(o2);
            return valueB-valueA;
        }
    }

    //比较器，用于牌面分值排序
    public class rankCompator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            int valueA = getPokerValueByRank(o1);
            int valueB = getPokerValueByRank(o2);
            return valueB-valueA;
        }
    }
}

