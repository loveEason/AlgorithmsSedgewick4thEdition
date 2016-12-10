package com.company.Chapter3_Searching.Section3_2_BinarySearchTrees;

import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Queue;

/**
 * 二叉查找树
 * Created by huxijie on 16-11-28.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;        //键
        private Value value;    //值
        private Node left,right;    //指向子树的链接
        private int N;              //以该结点为要根的子树中的结点总数

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private Node root;  //二叉查找树的根结点

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private Value get(Node x, Key key) {
        //在以x为根结点的子树中查找并返回key所对应的值
        //如果找不到则返回null
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        return get(root, key);
    }

//    //非递归实现
//    public Value get(Key key) {
//        Node x = root;
//        while (x != null) {
//            int cmp = key.compareTo(x.key);
//            if (cmp < 0) {
//                x = x.left;
//            } else if (cmp > 0) {
//                x = x.right;
//            } else {
//                return x.value;
//            }
//        }
//        return null;
//    }

    private Node put(Node x, Key key, Value value) {
        //如果key存在于以x为根的子树中则更新它的值
        //否则将以key和val为键值对的新结点插入到该子树中
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

//    //非递归实现
//    public void put(Key key, Value value) {
//        Node z = new Node(key, value, 1);
//        if (root == null) {
//            root = z;
//            return;
//        } else {
//            Node parent = null, x = root;
//            while (x != null) {
//                parent = x;
//                int cmp = key.compareTo(x.key);
//                if (cmp < 0) {
//                    x = x.left;
//                } else if (cmp > 0) {
//                    x = x.right;
//                } else {
//                    x.value = value;
//                    return;
//                }
//            }
//            int cmp = key.compareTo(parent.key);
//            if (cmp < 0) {
//                parent.left = z;
//            } else {
//                parent.right = z;
//            }
//            x = root;
//            while (x != z) {
//                x.N++;
//                int Tempcmp = key.compareTo(x.key);
//                if (Tempcmp < 0) {
//                    x = x.left;
//                } else if (Tempcmp > 0) {
//                    x = x.right;
//                } else {
//                    return;
//                }
//            }
//        }
//    }


    private Node min(Node x) {
        //返回最小结点
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    private Node max(Node x) {
        //返回最大结点
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    private Node floor(Node x, Key key) {
        //返回小于等于key的最大结点
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        } else if (cmp == 0) {
            return x;
        } else {
            Node t = floor(x.right, key);
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node ceiling(Node x, Key key) {
        //返回大于等于key的最小结点
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return ceiling(x.right, key);
        } else if (cmp == 0) {
            return x;
        } else {
            Node t = ceiling(x.left, key);
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node select(Node x, int k) {
        //返回排名为k的结点
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    public Key select(int k) {
        Node x = select(root, k);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private int rank(Node x, Key key) {
        //返回以x为根结点的子树中小于key的键的数量
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return size(x.left) + 1 + rank(x.right, key);
        } else {
            return size(x.left);
        }
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private Node deleteMin(Node x) {
        //删除最小键
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMax(Node x) {
        //删除最大键
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node delete(Node x, Key key) {
        //删除键为key的结点
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        //范围查找
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
        return;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private int height(Node x) {
        //递归求树的高度
        if (x == null) {
            return -1;
        } else {
            return 1 + Math.max(height(x.left), height(x.right));
        }
    }

    public int height() {
        return height(root);
    }

    //二叉树检查
    //编写一个递归的方法isBinaryTree(),接受一个结点Node作为参数。
    //如果以该结点为根的子树中的结点总数和计数器的值N相符则返回true，否则返回false
    private boolean isBinaryTree(Node x) {
        if (x == null) {
            return true;
        }
        if (x.N != (size(x.left) + size(x.right) + 1)) {
            return false;
        }
        return isBinaryTree(x.left) && isBinaryTree(x.right);
    }

    public boolean isBinaryTree() {
        return isBinaryTree(root);
    }

    //有序性检查
    //编写一个递归的方法isOrdered(),授受一个结点Node和min、max两个键作为参数。
    //如果以该结点 为根的子树中的所有结点都在min和max之间，min和max的确分别是树中的最小和最大的结点且二叉查找树的有序性对树中的所有键都成立，返回true。
    private boolean isOrdered(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }
        return isOrdered(x.left, min, x.key) && isOrdered(x.right, x.key, max);
    }

    public boolean isOrdered() {
        return isOrdered(root, null, null);
    }

    //等值键检查
    //编写一个方法hasNoDuplicates(),接受一个结点Node为参数。
    //如果以该结点为根的二叉查找树中不含有等值的键则返回true，否则返回false。假设树已经通过了前几道的检查
    private boolean hasNoDuplicates(Node x) {
        if (x == null) {
            return true;
        }
        if (x.key.compareTo(max(x.left).key) == 0 || x.key.compareTo(min(x.right).key) == 0) {
            return false;
        }
        return hasNoDuplicates(x.left) && hasNoDuplicates(x.right);
    }

    public boolean hasNoDuplicates() {
        return hasNoDuplicates(root);
    }

    //验证
    //编写一个方法isBST(),接受一个结点Node为参数。
    //若该结点是一个二叉查找树的根结点则返回true,否则返回false
    public boolean isBST(Node x) {
        if (!isBinaryTree(x)) {
            return false;
        }
        if (!isOrdered(x, min(x).key, max(x).key)) {
            return false;
        }
        if (!hasNoDuplicates(x)) {
            return false;
        }
        return true;
    }

    public boolean isBST() {
        return isBST(root);
    }

    //选择、排名检查
    //编写一个方法，对于0到size()-1之间的所有i，检查i和rank(select(i))是否相等，
    //并检查二叉查找树中的任意键key和select(rank(key))是否相等
    public boolean isRankConsistent() {
        for (int i=0;i<size();i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (key.compareTo(select(rank(key))) != 0) {
                return false;
            }
        }
        return true;
    }
}

