package com.lijun.learn.algorithm;

import java.util.Arrays;

/**
 * 排序算法
 *
 * @author : LiJun
 * @date : 2023-04-24 16:27
 **/
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] arr = {4, 2, 8, 6, 5, 1, 7, 11, 0};
        System.out.println("Before sort:" + Arrays.toString(arr));

//        directInsertionSort(arr);
//        shellSort(arr);
//        selectionSort(arr);
//        heapSort(arr);
//        bubbleSort(arr);
        quickSort(arr,0,arr.length-1);
        System.out.println("After sort: " + Arrays.toString(arr));
    }

    /**
     * 直接插入排序：
     *  从未排序的序列中取出一个元素，在已排序的队列中找到合适的位置插入
     * 步骤：
     *  1.使用for循环遍历数组，从第二个元素开始。
     *  2.将当前元素作为关键字key，将其插入到已经排序好的序列中。
     *  3.使用while循环将key与已排序的元素进行比较，并向右移动更大的元素。
     *  4.当找到一个位置j使得arr[j] <= key时，将key插入到arr[j+1]的位置上。
     * @param arr 数组
     */
    public static void directInsertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * 希尔排序:
     *  对直接插入排序的优化，它将待排序的序列按照一定步长进行分组，
     *  对每组元素进行插入排序，然后逐步缩小步长直到为1，最后使用一次插入排序完成排序。
     * 步骤:
     *  1.首先确定步长gap的初始值，通常为数组长度的一半。
     *  2.对于每个步长gap，从第gap个元素开始，将其与前面同一组内的元素进行比较，并按照插入排序的方式进行插入。
     *  3.不断减小步长gap，重复上述插入排序的过程，直到步长gap达到1，此时整个数组变成一个有序序列。
     *
     * 最外层套了一层步长的循环，其余代码和直接插入排序相同，只是把 1 换成 步长
     * @param arr 数组
     */
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > key) {
                    arr[j+gap] = arr[j];
                    j -= gap;
                }
                arr[j+gap] = key;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 选择排序：从未排序的序列中选择一个最小值或最大值，将其交换到未排序部分的最前面
     * @param arr 数组
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * 堆排序：利用数据结构堆的特性，小顶堆中堆顶为堆的最小值，每次取堆顶，然后再维护小顶堆
     * @param arr 数组
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        //构建堆，从最后一个非叶子节点开始
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr,n,i);
        }

        //arr[0] 就是堆顶也是最大值，将堆与最后一个元素互换，然后在从新堆化子序列
        for (int i = n-1; i >= 0; i--) {
            swap(arr,i,0);
            heapify(arr,i,0);
        }
    }

    /**
     * 向下调整为堆
     * @param arr 数组
     * @param n 数组大小
     * @param parent 父节点
     */
    public static void heapify(int[] arr,int n,int parent) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int max = parent;

        //找出三个节点中最大的
        if (left < n && arr[left] > arr[max]){
            max = left;
        }
        if (right < n && arr[right] > arr[max]){
            max = right;
        }
        //说明最大节点不为父节点，则将最大值交换到父节点
        if (max != parent){
            swap(arr,max,parent);
            //因为发生了交换，所以子树也要验证堆的性质
            heapify(arr,n,max);
        }
    }





    /**
     * 冒泡排序
     * 分类：交换排序
     * @param arr 数组
     */
    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = len ; i > 1; i--) {
            for (int j = 0; j < i -1; j++) {
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 快速排序：
     *      采用分治的思想，将大的问题转换为小问题
     * 分类：
     *      交换排序
     * 步骤：
     *      1.选取序列中间的一个元素 mid，从序列的头部和尾部开始向中间遍历，
     *        将左边大于 mid 的和右边小于 mid 的交换，直到左边都小于 mid ，右边都大于 mid
     *      2.对左右两边的序列重复1步骤
     *
     * @param arr 数组
     */
    private static void quickSort(int[] arr,int start,int end){
        if (start >= end){
            return;
        }
        int m = (start + end) / 2;
        int l = start;
        int r = end;
        while (l < r){
            if (arr[l] >= arr[m] && arr[r] <= arr[m]){
                swap(arr,l,r);
                if (l == m){
                    m = r;
                }else if (r == m){
                    m = l;
                }
            }
            if (arr[l] < arr[m]){
                l++;
            }
            if (arr[r] > arr[m]){
                r--;
            }
        }
        quickSort(arr, start, m - 1);
        quickSort(arr, m + 1, end);
    }



    public static void swap(int[] heap,int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

}
