package com.btpj.leetcode.array;

/**
 * 排序算法
 *
 * @author BTPJ  2021/5/17
 */
public class Sort {
    private static final int[] arr = {12, 13, 4, 7, 2, 21, 8};

    public static void main(String[] args) {
        // 冒泡排序
        // maoPaoSort(arr);

        // 选择排序
        // chooseSort(arr);

        // 插入排序
         insertSort(arr);

        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

    /**
     * 冒泡排序
     * 比较相邻的元素，如果第一个比第二大，交换
     * 对每一对相邻元素重复相同的操作
     *
     * @param arr 要排序的数组
     */
    private static void maoPaoSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 依次找到最小、第二小、第三小......的元素放置在第1、2、3......位上面
     *
     * @param arr 要排序的数组
     */
    private static void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * 左边表示已排序右边表示未排序，将右边的数移到左边去比较
     *
     * @param arr 要排序的数组
     */
    private static void insertSort(int[] arr) {
        int j;
        for (int i = 1; i < arr.length; i++) { // i表示第一个未排序的元素，也就是需要插入的元素
            int temp = arr[i]; // 每次将要插入的元素保存到temp
            for (j = i - 1; j >= 0; j--) { // 拿着已排序序列的元素降序逐一与有序序列进行比较寻找插入点
                if (temp > arr[j]) {
                    break;
                } else {
                    arr[j + 1] = arr[j];
                }
            }
            arr[j + 1] = temp; //  将要插入的temp插入到插入点之后一个元素
        }
    }
}
