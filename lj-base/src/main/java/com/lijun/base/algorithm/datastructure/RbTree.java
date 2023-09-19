package com.lijun.base.algorithm.datastructure;

import java.util.Arrays;

/**
 * @author : LiJun
 * @date : 2020-09-05 18:16
 *
 **/
public class RbTree {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,0,0,0};
        int[] arr2 = {2,5,6};

//        merge(arr1,3,arr2,3);
        te(arr1,3,arr2,3);
        print(arr1);
    }
    public static void te(int[] nums1, int m, int[] nums2, int n){
        for (int i=0;i<n;i++){
            nums1[m] = nums2[i];
            m++;
        }
        Arrays.sort(nums1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int start = 0;
        for (int n2 : nums2) {
            for (;start < m;start++){
                if (n2 <= nums1[start]){
                    add(nums1,start,n2,m);
                    break;
                }
            }
            if (start == m){
                //未找到数据，直接插入尾部
                nums1[m] = n2;
            }
            start++;
            m++;
        }
        System.arraycopy(nums2, 0, nums1, 0, m);
    }
    public static void add(int[] arr,int insertIndex,int insertValue,int end){
        int lastIdx = end - 1;
        while (lastIdx >= insertIndex){
            int temp = arr[lastIdx];
            arr[lastIdx+1] = temp;
            lastIdx--;
        }
        arr[insertIndex] = insertValue;
    }


    public static void print(int[] arr){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i=0 ;i<arr.length;i++) {

            if (i == arr.length-1){
                builder.append(arr[i]);
            }else {
                builder.append(arr[i]).append(",");
            }
        }
        builder.append("]");
        System.out.println(builder.toString());
    }
}
