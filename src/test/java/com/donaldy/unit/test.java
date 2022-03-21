package com.donaldy.unit;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

public class test {

    public static void main(String[] args) {

        /*Hashids hashids = new Hashids("Donald", 10);

        System.out.println(hashids.encode(221432423543535L));

        System.out.println(Arrays.toString(hashids.decode("B9g7YNw0EX")));*/

        double a = Math.pow(64, 63);
        System.out.println(a);

        double b = Math.pow(63, 64);
        System.out.println(b);
    }

    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public void addNum(int num) {

        priorityQueue.add(num);
    }

    public double findMedian() {

        if (priorityQueue.isEmpty()) return 0;

        int size = priorityQueue.size();

        if (size % 2 == 0) {

            return findEven(size / 2);
        }

        return findOdd(size / 2);
    }

    private double findOdd(int index) {
        Iterator iterator = priorityQueue.iterator();
        for (int i = 0; i < index; ++i) {
            iterator.next();
        }
        return (double) iterator.next();
    }

    private double findEven(int index) {
        Iterator iterator = priorityQueue.iterator();
        int pre = 0;
        for (int i = 0; i < index; ++i) {
            pre = (int) iterator.next();
        }
        int now = (int) iterator.next();

        return (pre + now) / 2;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
