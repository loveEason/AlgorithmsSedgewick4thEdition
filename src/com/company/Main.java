package com.company;

import edu.princeton.cs.algs4.*;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //练习1.1.
        //1.1.1
//        System.out.println((0+15)/2);
//        System.out.println(2.0e-6*100000000.1);
//        System.out.println(true&&false || true&&true);
//        //1.1.2
//        System.out.println((1+2.236)/2);
//        System.out.println(1+2+3+4.0);
//        System.out.println(4.1>=4);
//        System.out.println(1+2+"3");

        //1.1.11
//        Boolean[][] boolenArray = new Boolean[4][4];
//        for(int i=0;i<4;i++) {
//            for(int j=0;j<4;j++) {
//                if(j<4-i) boolenArray[i][j] = true;
//                else boolenArray[i][j] = false;
//            }
//        }
//        printBoolenBinary(boolenArray,4,4);

        //1.1.12
//        int[] a = new int[10];
//        for(int i=0;i<10;i++) {
//            a[i] = 9-i;
//        }
//        for(int i=0;i<10;i++) {
//            a[i] = a[a[i]];
//        }
//        for(int i=0;i<10;i++) {
//            System.out.println(a[i]);
//        }

        //1.1.13
//        int n=4;
//        int[][] a = new int[n][n];
//        int digit=1;
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                a[i][j] = digit;
//                digit++;
//            }
//        }
//        System.out.println("before:");
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(a[i][j]+" ");
//            }
//            System.out.println();
//        }
//        for(int i=0;i<n;i++)
//            for(int j=i;j<n;j++) {
//                int tmp = a[i][j];
//                a[i][j] = a[j][i];
//                a[j][i] =tmp;
//            }
//        System.out.println("after:");
//        System.out.println("before:");
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(a[i][j]+" ");
//            }
//            System.out.println();
//        }

        //1.1.15
//        int a[] = {0,1,0,2,3,4,5,0,6,2,7,5,1,3,4,8,0,1,5,8,0,6,4,3,7};
//        int[] result = histogram(a,9);
//        for (int i: result
//             ) {
//            System.out.println(i);
//        }

        //1.1.16
//        System.out.println(exR1(6));

        //1.1.18
//        System.out.println(mystery(2,25));
//        System.out.println(mystery(3,11));

        //1.1.20
//        System.out.println(calculateLn(10));

        //1.1.22
//        int[] a = {1,2,3,4,5,6,7,8,9,10};
//        int result = rank(12,a);
//        if(result < 0) System.out.println("the key doesn't exist in the array.");
//        else System.out.println("the index of the key in the array is: "+result);

        //1.1.24
//        int p = 1111111;
//        int q = 1234567;
//        int result = gcd(p,q);
//        System.out.println(p+"和"+q+"的最大公约数是："+result);

        //1.1.30
//        System.out.print("input an integer N: ");
//        Scanner scanner = new Scanner(System.in);
//        int N = Integer.parseInt(scanner.nextLine());
//        boolean[][] a = new boolean[N][N];
//        for(int i=0;i<N;i++) {
//            for(int j=0;j<N;j++) {
//                if(gcd(i,j) == 1) a[i][j] = true;
//                else a[i][j] = false;
//            }
//        }
//        for(int i=0;i<N;i++) {
//            for (int j=0;j<N;j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }

//        System.out.println("input:");
//        Scanner scanner = new Scanner(System.in);
//        int T = Integer.parseInt(scanner.nextLine());
//        Accumulator a = new Accumulator();
//        for(int t = 0;t<T;t++) {
//            a.addDataValue(Math.random());
//        }
//        System.out.println(a);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("input:");
//        int n=0;
//        double array[] = new double[5];
//        while (scanner.hasNext()) {
//            array[n] = Double.parseDouble(scanner.next());
//            n++;
//        }
//        Interval1D xinterval = new Interval1D(array[0],array[1]);
//        Interval1D yinterval = new Interval1D(array[2],array[3]);
//        Interval2D box = new Interval2D(xinterval,yinterval);
//        box.draw();
//        Counter c = new Counter("hits");
//        for(int t=0;t<array[4];t++) {
//            double x = Math.random();
//            double y = Math.random();
//            Point2D p = new Point2D(x,y);
//            if(box.contains(p)) c.increment();
//            else p.draw();
//        }
//        StdOut.println(c);
//        StdOut.println(box.area());

        //1.3.2
//        System.out.println("input strings:");
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] strings = read.split(" ");
//        Stack<String> stringStack = new Stack<>();
//        for (String s:strings) {
//            if (s.equals("-")) System.out.print(stringStack.pop()+" ");
//            else stringStack.push(s);
//        }
//        System.out.println();

        //1.3.4
//        System.out.println("input brackets:");
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] strings = read.split("");
//        Stack<String> bracketsStack = new Stack<>();
//        boolean judge = true;
//        for (String s:strings) {
//            if (s.equals("(") || s.equals("[") || s.equals("{")) bracketsStack.push(s);
//            else if (s.equals(")")) {
//                if (!(bracketsStack.pop()).equals("(")) {
//                    judge = false;
//                    System.out.println("brackets are not matched");
//                    break;
//                }
//            }
//            else if (s.equals("]")) {
//                if (!(bracketsStack.pop()).equals("[")) {
//                    judge = false;
//                    System.out.println("bracket are not matched");
//                    break;
//                }
//            }
//            else if (s.equals("}")) {
//                if (!(bracketsStack.pop()).equals("{")) {
//                    judge = false;
//                    System.out.println("brackets are not matched");
//                    break;
//                }
//            }
//            else {
//                judge = false;
//                System.out.println("false strings");
//            }
//        }
//        if (judge) System.out.println("brackets are matched!");

        //1.3.5
//        int N = 50;
//        Stack<Integer> stack = new Stack<>();
//        while (N>0) {
//            stack.push(N%2);
//            N = N/2;
//        }
//        for (int d:stack) System.out.print(d);
//        System.out.println();

        //1.3.9
//        System.out.println("input:");
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] strings = read.split(" ");
//        Stack<String> ops = new Stack<>();
//        Stack<String> val = new Stack<>();
//        for (String s:strings) {
//            if (s.equals("("));
//            else if (s.equals("+") ||
//                    s.equals("-") ||
//                    s.equals("*") ||
//                    s.equals("/")) {
//                ops.push(s);
//            }
//            else if (s.equals(")")) {
//                String op = ops.pop();
//                String v = val.pop();
//                v = String.format("( %s %s %s )",val.pop(),op,v);
//                val.push(v);
//            }
//            else {
//                val.push(s);
//            }
//        }
//        System.out.println("result: "+val.pop());

        //1.3.10
//        System.out.println("input:");
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] strings = read.split(" ");
//        Stack<String> stack = new Stack<>();
//        for (String s:strings) {
//            if (s.equals("("));
//            else if (s.equals("+") ||
//                    s.equals("-") ||
//                    s.equals("*") ||
//                    s.equals("/")) stack.push(s);
//            else if (s.equals(")")) System.out.print(stack.pop()+" ");
//            else System.out.print(s+" ");
//        }
//        System.out.println();

        //1.3.15
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] string_2 = read.split(",");
//        int k = Integer.parseInt(string_2[0]);
//        String[] strings = string_2[1].split(" ");
//        int n=0;
//        Queue<String> queue = new Queue<>();
//        for (String s:strings) {
//            queue.enqueue(s);
//            n++;
//        }
//        for (int i=0;i<n-k;i++) {
//            queue.dequeue();
//        }
//        System.out.println("the No."+k+" is: "+queue.dequeue());

        //链表练习
//        Linked_List<Integer> linked_list = new Linked_List<>();
//        for (int i=1;i<6;i++) {
//            linked_list.add(i);
//        }
//        System.out.println("first show all:");
//        linked_list.show();
//        System.out.println("delete the tail:");
//        linked_list.deleteTail();
//        linked_list.show();
//        System.out.println("delete the No.3");
//        linked_list.delete(3);
//        linked_list.show();
//        System.out.println("find the key '1':");
//        System.out.println(linked_list.find(1));
//        System.out.println("remove all nodes whose item is key 2:");
//        linked_list.add(3);
//        linked_list.add(2);
//        linked_list.remove(2);
//        linked_list.show();

        //1.3.33
//        Deque<Integer> deque = new Deque<>();
//        for (int i=1;i<6;i++) {
//            deque.pushLeft(i);
//        }
//        for (Integer i:deque) {
//            System.out.println(i);
//        }
//        System.out.println("\n"+deque.popLeft()+"\n");
//        System.out.println(deque.popRight()+"\n");
//        for (Integer i:deque) {
//            System.out.println(i);
//        }

        //1.3.34
//        RandomBag<Integer> randomBag = new RandomBag<>();
//        for (int i=0;i<6;i++) {
//            randomBag.add(i);
//        }
//        for (int i:randomBag) {
//            System.out.println(i);
//        }

        //1.3.39
//        RingBuffer<Integer> ringBuffer = new RingBuffer<>(6);
//        for (int i=0;i<6;i++) {
//            ringBuffer.enqueue(i);
//        }
//        for (int i : ringBuffer) {
//            System.out.println(i);
//        }
//        System.out.println("next:");
//        ringBuffer.dequeue();
//        ringBuffer.enqueue(6);
//        for (int i : ringBuffer) {
//            System.out.println(i);
//        }
//        System.out.println("next:");
//        System.out.println(ringBuffer.dequeue());
//        System.out.println("new:");
//        for (int i : ringBuffer) {
//            System.out.println(i);
//        }
//        System.out.println("next:");
//        ringBuffer.enqueue(7);
//        for (int i : ringBuffer) {
//            System.out.println(i);
//        }

        //1.3.40
//        MoveToFront moveToFront = new MoveToFront();
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] strings = read.split(" ");
//        for (String s : strings) {
//            moveToFront.insert(s);
//        }
//        for (Object s : moveToFront) {
//            System.out.println((String)s);
//        }

        //1.3.41
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] strings = read.split(" ");
//        Queue<String> queue = new Queue<>();
//        for (String s:strings) {
//            queue.enqueue(s);
//        }
//        System.out.println("the original queue:");
//        for (String s : queue) {
//            System.out.println(s);
//        }
//        Queue<String> newQueue = new Queue<>(queue);
//        System.out.println("the copyed queue:");
//        for (String s : newQueue) {
//            System.out.println(s);
//        }
//        System.out.println("let original queue add new item:");
//        queue.enqueue("hello");
//        System.out.println("now test whether they are independent?");
//        System.out.println("the original queue:");
//        for (String s : queue) {
//            System.out.println(s);
//        }
//        System.out.println("the copyed queue:");
//        for (String s : newQueue) {
//            System.out.println(s);
//        }

        //1.3.42
//        System.out.println("input strings:");
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        String[] strings = read.split(" ");
//        Stack<String> stringStack = new Stack<>();
//        for (String s:strings) {
//            stringStack.push(s);
//        }
//        Stack<String> newStack = new Stack<>(stringStack);
//        System.out.println("the original stack:");
//        for (String s : stringStack) {
//            System.out.println(s);
//        }
//        System.out.println("the copyed stack:");
//        for (String s : newStack) {
//            System.out.println(s);
//        }
//        System.out.println("let original stack add new item:");
//        stringStack.push("hello");
//        System.out.println("now test whether they are independent?");
//        System.out.println("the original stack:");
//        for (String s : stringStack) {
//            System.out.println(s);
//        }
//        System.out.println("the copyed stack:");
//        for (String s : newStack) {
//            System.out.println(s);
//        }

        //1.3.43
//        Scanner scanner = new Scanner(System.in);
//        String read = scanner.nextLine();
//        FileList fileList = new FileList(read);
//        fileList.show();

        //1.4.18
        int[] a = new int[]{1,2,3,5,4,2,3,1,4,2,5};
        localMin(a,a.length);
    }

    public static void printBoolenBinary(Boolean[][] array,int i,int j) {
        System.out.print("  ");
        for(int k=0;k<i;k++) {
            System.out.print(k+" ");
        }
        System.out.println();
        for(int k=0;k<i;k++) {
            System.out.print(k+" ");
            for(int m=0;m<j;m++) {
                System.out.print(array[k][m]?"*":" ");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static int[] histogram(int[] a,int M) {
        int[] result = new int[M];
        for(int i=0;i<M;i++) {
            int count=0;
            for(int j=0;j<a.length;j++) {
                if(a[j] == i) count++;
            }
            result[i] = count;
        }

        return result;
    }

    public static String exR1(int n) {
        if(n<=0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }

    public static int mystery(int a,int b) {
        if(b == 0) return 0;
        if(b % 2 == 0) return mystery(a+a,b/2);
        return mystery(a+a,b/2) + a;
    }

    public static double calculateLn(int N) {
        if(N==1) return 0;
        return Math.log10(N) + Math.log10(N-1);
    }

    public static int rank(int key,int[] a) {
        int depth=0;
        return rank(key,a,0,a.length - 1,depth);
    }

    public static int rank(int key,int[] a,int lo,int hi,int depth) {
        depth++;
        System.out.println("No."+depth+":"+" lo="+lo+" hi="+hi);
        if(lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if(key < a[mid]) return rank(key,a,lo,mid-1,depth);
        else if(key > a[mid]) return rank(key,a,mid+1,hi,depth);
        else return mid;
    }

    public static int gcd(int p,int q) {
        System.out.println("p:"+p+",q:"+q);
        if (q == 0) return p;
        int r = p % q;
        return gcd(q,r);
    }

    public static void localMin(int[] a,int n) {
        if (a[n / 2] < a[n / 2 - 1] && a[n / 2] < a[n / 2 + 1]) {
            System.out.println("the local min is No."+n/2);
            return;
        } else if (a[n / 2] >= a[n / 2 - 1]) {
            n = n/2-1;
            localMin(a,n);
        } else {
            n = n/2+1;
            localMin(a,n);
        }
    }
}
