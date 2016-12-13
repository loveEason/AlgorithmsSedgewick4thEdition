package com.company.Chapter3_Searching.Section3_3_BalancedSearchTrees;


import java.util.*;

/**
 * 红黑树
 * Created by huxijie on 16-12-10.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;


    private class Node {
        Key key;            //键
        Value value;        //相关联的值
        Node left, right;    //左右子树
        int N;              //这棵子树中的结点总数
        boolean color;      //由其父结点指向它的链接的颜色

        Node(Key key, Value value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    public int size() {
        return size(root);
    }

    //测试结点x和父结点之间的链接颜色
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    public boolean isEmpty() {
        return root == null;
    }


    //左旋转h的右链接
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    //右旋转h的左链接
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    //转换一个结点的两个红色子结点的颜色，将父结点的颜色由黑变红
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    //插入操作
    //在沿着插入点到根结点的路径向上移动时在所经过的每个结点中顺序完成以下操作，就能完成插入操作：
    //1.如果右子结点是红色的而左子结点是黑色的，进行左旋转
    //2.如果左子结点是红色的且它的左子结点也是红色的，进行右旋转
    //3.如果左右子结点均为红色，进行颜色转换
    private Node put(Node h, Key key, Value value) {
        if (h == null) {    //标准的插入操作，和父结点用红链接相连
            return new Node(key, value, 1, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.value = value;
        }


        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    //从h.right中借一个结点，将h.left或者h.left.left变红
    private Node moveRedLeft(Node h) {
        flipColors(h);  //颜色转换之后，h.left和h.right都将变为红
        if (isRed(h.right.left)) {  //这种情况是对应将h.left.left变红
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        //如果不是上述情况，则对应将h.left变红
        return h;
    }

    //从h.left中借一个结点，将h.right或者h.right.riht变红
    private Node moveRedRight(Node h) {
        flipColors(h);  //颜色转换之后，h.left和h.right都将变为红
        if (isRed(h.left.left)) {
            //这种情况是对应将h.right.right变红
            //判断h.left.left是否为红，若是，对h进行右旋转左链接，且颜色要转换
            //每次都是判断h.left.left，因为在借之前，只可能出现红色左链接
            h = rotateRight(h);
            flipColors(h);
        }
        //如果不是上述情况，则对应将h.right变红
        return h;
    }

    //修复红黑树
    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node deleteMin(Node h) {
        //递归结束条件，到达左边界
        if (h.left == null) {
            return null;
        }
        //保证h.left或者h.left.left为红结点
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        //递归地在左子树中删除
        h.left = deleteMin(h.left);
        //删除后修复红色右结点
        return balance(h);
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {
        //使树出现红色右链接。必须先这一步，因为不借的话是不可能有红色右链接的
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        //递归结束条件，到达右边界
        if (h.right == null) {
            return null;
        }
        //保证h.right或者h.right.left为红结点
        if (!isRed(h.right) && !isRed(h.right.left)) {
            //在这里和删除最小键不一样。是判断h.right.left是否为红结点，那是因为不可能出现h.right.right是红结点的情况
            //并且此时的h.right.left如果是红结点的话，会通过下一次递归的上面的一次右旋转，可以得到h.right.right是右结点的效果
            h = moveRedRight(h);
        }
        //递归地在右子树中删除
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) { //递归在左子树中删除
            if (!isRed(h.left) && !isRed(h.left.left)) {    //确保删除的结点不为2结点
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {    //确保在右子树中能出现红色右孩子
                h = rotateRight(h);
            }

            if (key.compareTo(h.key) == 0) {
                //待删除的结点在树底
                if (h.right == null) {
                    return null;
                } else {        //待删除的结点不在树底
                    Node x = min(h.right);
                    h.key = x.key;
                    h.value = x.value;
                    h.right = deleteMin(h.right);
                }
            } else {    //递归在右子树中删除
                //若把“递归在右子树中删除”放在“等于”之前，h.right.left可能出现空指针异常，因为缺少递归结束判断条件
                if (!isRed(h.right) && !isRed(h.right.left)) {      //确保删除的结点不为2结点
                    h = moveRedRight(h);
                }
                h.right = delete(h.right, key);
            }
        }
        //修复
        return balance(h);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;         //确保删除后头节点是黑色，因为可能把头节点变红
        }
    }

    //在以x为根结点的子树中查找并返回key所对应的值
    //如果找不到则返回null
    private Value get(Node x, Key key) {
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
            throw new IllegalArgumentException("argument to get() is null");
        }
        return get(root, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public int height() {
        return height(root);
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty symbol table");
        }
        return min(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("called max() with empty symbol table");
        }
        return max(root).key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return floor(x.left, key);
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
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("called floor() with empty symbol table");
        }
        Node x = floor(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp > 0) {
            return ceiling(x.right, key);
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
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("called ceiling() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node select(Node x, int k) {
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
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        Node x = select(root, k);
        return x.key;
    }

    private int rank(Node x, Key key) {
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
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(root, key);
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.add(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new LinkedList<Key>();
        }
        return keys(min(), max());
    }

    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
        }
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    //选择、排名检查
    //编写一个方法，对于0到size()-1之间的所有i，检查i和rank(select(i))是否相等，
    //并检查二叉查找树中的任意键key和select(rank(key))是否相等
    public boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
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

    //二叉树检查
    //编写一个递归的方法isBinaryTree(),接受一个结点Node作为参数。
    //如果以该结点为根的子树中的结点总数和计数器的值N相符则返回true，否则返回false
    private boolean isSizeConsistent(Node x) {
        if (x == null) {
            return true;
        }
        if (x.N != (size(x.left) + size(x.right) + 1)) {
            return false;
        }
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    public boolean isSizeConsistent() {
        return isSizeConsistent(root);
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

    //红结点检查
    //是否有红色右结点
    //是否存在路径上连续出现两个红结点
    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }
        if (isRed(x.right)) {
            return false;
        }
        if (x != root && isRed(x) && isRed(x.left)) {
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    public boolean is23() {
        return is23(root);
    }

    //平衡性检查
    //是否所有路径上从根到叶子结点的黑色结点个数相等
    public boolean isBalanced() {
        int black = 0;      //根到最小键路径上的黑色结点个数
        Node x = root;
        while (x != null) {
            if (!isRed(x)) {
                black++;
            }
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        if (x == null) {
            return black == 0;
        }
        if (!isRed(x)) {
            black--;
        }
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public boolean check() {
        if (!isOrdered()) {
            System.out.println("Not in symmetric order");
        }
        if (!isSizeConsistent()) {
            System.out.println("Subtree counts not consistent");
        }
        if (!isRankConsistent()) {
            System.out.println("Ranks not consistent");
        }
        if (!is23()) {
            System.out.println("Not a 2-3 tree");
        }
        if (!isBalanced()) {
            System.out.println("Not balanced");
        }
        return isOrdered() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    //层序遍历所有结点的键
    public void layerTraversal(Node h) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(h);
        Node curNode;
        Node nlast = null;
        Node last = h;
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            System.out.print(curNode.key + " ");
            if (curNode.left != null) {
                nlast = curNode.left;
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                nlast = curNode.right;
                queue.add(curNode.right);
            }
            if (curNode == last) {
                System.out.println();
                last = nlast;
            }
        }
    }

    //层序遍历结点的颜色（是否为红）
    public void layerTraversalColor(Node h) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(h);
        Node curNode;
        Node nlast = null;
        Node last = h;
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            System.out.print(curNode.color + " ");
            if (curNode.left != null) {
                nlast = curNode.left;
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                nlast = curNode.right;
                queue.add(curNode.right);
            }
            if (curNode == last) {
                System.out.println();
                last = nlast;
            }
        }
    }

    //先序遍历所有结点的键
    public void preOrderTraversal(Node h) {
        Stack<Node> stack = new Stack<>();
        Node curNode = null;
        stack.push(h);
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            System.out.print(curNode.key + " ");
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
        }
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> redBlackBST = new RedBlackBST<>();
        System.out.print("请输入结点个数：");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println("请依次输入" + num + "个字母：");
        for (int i=1;i<=num;i++) {
            String key = scanner.next();
            redBlackBST.put(key, i);
        }

        System.out.println("层次遍历结点的颜色（是否为红）:");
        redBlackBST.layerTraversalColor(redBlackBST.root);
        System.out.println();

        System.out.println("层次遍历结点的键：");
        redBlackBST.layerTraversal(redBlackBST.root);
        System.out.println();

        System.out.println("先序遍历：");
        redBlackBST.preOrderTraversal(redBlackBST.root);
        System.out.println();

        System.out.println("测试删除任意键");
        System.out.println("请输入要删除的键：");
        String key = scanner.next();
        redBlackBST.delete(key);

        System.out.println("层次遍历结点的颜色（是否为红）:");
        redBlackBST.layerTraversalColor(redBlackBST.root);
        System.out.println();

        System.out.println("层次遍历结点的键：");
        redBlackBST.layerTraversal(redBlackBST.root);
        System.out.println();

        System.out.println("先序遍历：");
        redBlackBST.preOrderTraversal(redBlackBST.root);
        System.out.println();

    }
}
