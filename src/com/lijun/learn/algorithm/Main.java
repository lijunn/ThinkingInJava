package com.lijun.learn.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : LiJun
 * @date : 2019-09-02 09:56
 * 递推
 **/
public class Main {

    public static void main(String[] args){

//        char[] str = {'h','e','l','l','o'};
//        helper(0,str);
//        swap(0,str.length-1,str);

//        System.out.println(getValue(5,1));

//        List<List<Integer>> list = new ArrayList<>();
//        generateRow(list,100);
//        System.out.println(list);



//        System.out.println(generateRow(null,1,5));

        ListNode link = createLink(null, 0);
        printLink(link);
        System.out.println(" ");
        printLink(reverseLink(link));

        int[] s = new int[3];

    }

    /**
     * 字符串反转
     * @param index
     * @param str
     */
    public static void helper(int index,char[] str){
        if(str==null || index >= str.length){
            return;
        }
        helper(index+1,str);
        System.out.println(str[index]);
    }

    /**
     * 输入字符反转
     * @param indexL
     * @param indexR
     * @param s
     */
    public static void swap(int indexL,int indexR,char[] s){
        if(s==null || indexL>=indexR){
            return;
        }
        swap(indexL+1,indexR-1,s);
        char tmp = s[indexL];
        s[indexL] = s[indexR];
        s[indexR] = tmp;
    }


    /**
     * 杨辉三角
     * @param i 行数
     * @param j 列数
     * @return
     */
    public static int getValue(int i,int j){

        if(i==0 || j==0){
            return 0;
        }
        if(i==j || j==1){
            return 1;
        }

        return getValue(i-1,j)+getValue(i-1,j-1);
    }

    /**
     * 生成杨辉三角
     * @param list 集合
     * @param numRows 行数
     */
    public static void generateRow(List<List<Integer>> list,int numRows){

        if (list.size()==numRows){
            return;
        }

        List<Integer> rowPre;
        if (list.size()==0){
            rowPre = new ArrayList<>();
            rowPre.add(1);
            list.add(rowPre);
        }else {
            rowPre = list.get(list.size() - 1);
            List<Integer> row = new ArrayList();
            for (int j=0;j<=list.size();j++){

                if (j==0 || j==list.size()){
                    row.add(1);
                }else {
                    row.add(rowPre.get(j)+rowPre.get(j-1));
                }

            }

            list.add(row);
        }

        generateRow(list,numRows);
    }

    public static List<Integer> generateRow(List<Integer> rowPre,int rowIndex,int numRows){
        if (rowPre == null){
            rowPre = new ArrayList<>();
        }
        if (rowPre.size()>=numRows){
            return rowPre;
        }

        if (rowPre.size()==0){
            rowPre.add(1);
        }else {
            List<Integer> row = new ArrayList();
            for (int j=0;j<rowIndex;j++){

                if (j==0 || j==rowIndex-1){
                    row.add(1);
                }else {
                    row.add(rowPre.get(j)+rowPre.get(j-1));
                }

            }
            rowPre.clear();
            rowPre.addAll(row);
        }
        generateRow(rowPre,rowIndex+1,numRows);

        return rowPre;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public static ListNode reverseLink(ListNode head) {
        if (head.next==null){
            return head;
        }

        ListNode next = head.next;
        ListNode newHead = reverseLink(next);

        next.next = head;
        head.next = null;

        return newHead;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public static void printLink(ListNode head){
        if (head == null){
            return;
        }

        System.out.print(head.val+",");
        printLink(head.next);
    }

    public static ListNode createLink(ListNode head,int linkSize){
        if (linkSize>5){
            return head;
        }
        if (head==null){
            head = new ListNode(linkSize);
        }
        linkSize++;
        head.next = new ListNode(linkSize);
        createLink(head.next,linkSize);
        return head;
    }

    /**
     * 斐波那契数列
     * @param N
     * @return
     */
    HashMap<Integer,Integer> cache = new HashMap<>();
    public int fib(int N) {
        if (cache.containsKey(N)){
            return cache.get(N);
        }
        int result;
        if(N<2){
            result =  N;
        }else{
            result = fib(N-1)+fib(N-2);
        }

        cache.put(N,result);
        return result;
    }
}
