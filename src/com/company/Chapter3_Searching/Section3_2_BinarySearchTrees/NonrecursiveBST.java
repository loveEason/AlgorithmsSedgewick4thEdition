package com.company.Chapter3_Searching.Section3_2_BinarySearchTrees;

import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Queue;
import com.company.Chapter1_Fundamentals.Section1_3_BagsQueuesStacks.Stack;

/**
 * 3.2.14
 * 二叉查找树的非递归实现
 * Created by huxijie on 16-11-29.
 */
public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private int N;
        private Node left, right;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private Node root;

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public void put(Key key, Value value) {
        Node z = new Node(key, value, 1);
        if (root == null) {
            root = z;
            return;
        } else {
            Node parent = null, x = root;
            while (x != null) {
                parent = x;
                int cmp = key.compareTo(x.key);
                if (cmp < 0) {
                    x = x.left;
                } else if (cmp > 0) {
                    x = x.right;
                } else {
                    x.value = value;
                    return;
                }
            }
            int cmp = key.compareTo(parent.key);
            if (cmp < 0) {
                parent.left = z;
            } else {
                parent.right = z;
            }
            x = root;
            while (x != z) {
                x.N++;
                int Tempcmp = key.compareTo(x.key);
                if (Tempcmp < 0) {
                    x = x.left;
                } else if (Tempcmp > 0) {
                    x = x.right;
                } else {
                    return;
                }
            }
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }

    public Key min() {
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public Key max() {
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public Key floor(Key key) {
        Node parent = null;
        Node x = root;
        Node t = null;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                t = x;
                x = x.right;
            } else {
                return x.key;
            }
        }
        if (t != null) {
            return t.key;
        } else {
            return parent.key;
        }
    }

    public Key ceiling(Key key) {
        Node parent = null;
        Node x = root;
        Node t = null;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp < 0) {
                t = x;
                x = x.left;
            } else {
                return x.key;
            }
        }
        if (t != null) {
            return t.key;
        } else {
            return parent.key;
        }
    }

    public int rank(Key key) {
        Node x = root;
        Node parent = null;
        int count = 0;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                count += size(x.left)+1;
                x = x.right;
            } else {
                if (x.left != null) {
                    count += size(x.left);
                }
                break;
            }
        }
        return count;
    }

    public Key select(int k) {
        Node x = root;
        Node parent = null;
        while (x != null) {
            parent = x;
            int t = size(x.left);
            if (t >= k) {
                x = x.left;
            } else if (t < k) {
                k = k - t - 1;
                if (k == 0) {
                    return x.key;
                }
                x = x.right;
            }
        }
        return null;
    }

    public Iterable<Key> keys() {
        Stack<Node> stack = new Stack<Node>();
        Queue<Key> queue = new Queue<Key>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            } else {
                x = stack.pop();
                queue.enqueue(x.key);
                x = x.right;
            }
        }
        return queue;
    }
}
