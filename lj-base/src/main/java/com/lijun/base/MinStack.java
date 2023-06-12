package com.lijun.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MinStack {

    public static void main(String[] args) {
        Node n0 = new Node(7);
        Node n1 = new Node(13);
        Node n2 = new Node(11);
        Node n3 = new Node(10);
        Node n4 = new Node(1);

        n0.next = n1;
        n1.next = n2;
        n1.random = n0;
        n2.next = n3;
        n2.random = n4;
        n3.next = n4;
        n3.random = n2;
        n4.random = n0;

        print(n0);

        Node node = copyRandomList(n0);


        print(node);
    }

    private static void print(Node head){
        while (head !=null){
            System.out.print(head.val+"--->");
            head = head.next;
        }
        System.out.println("  ");
    }

    public static Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }

        ArrayList<Node> nodeList = new ArrayList<>();
        ArrayList<Node> newNodeList = new ArrayList<>();

        Node cursor = head;
        Node preCursor = null;
        while (cursor != null){
            Node newCursor = new Node(cursor.val);

            nodeList.add(cursor);
            newNodeList.add(newCursor);

            if (preCursor != null){
                preCursor.next = newCursor;
            }
            preCursor = newCursor;

            cursor = cursor.next;
        }

        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            Node newNode = newNodeList.get(i);
            newNode.random = node.random == null ? null : newNodeList.get(nodeList.indexOf(node.random));
        }

        return newNodeList.get(0);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
