package array;

import java.util.Arrays;

/**
 * 原地删除（空间复杂度O(1)）有序数组中的重复项
 * 给你一个升序排列的数组nums ，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。元素的相对顺序应该保持一致
 * 链接：<a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">...</a>
 *
 * @author BTPJ  2023/3/28
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
//        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] arr = {1, 1, 2};
        System.out.println(removeDuplicates(arr));
    }

    /**
     * 双指针发
     *
     * @param arr 数组
     * @return 数组
     */
    private static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int slowPoint = 1, fastPoint = 1;
        while (fastPoint < arr.length) {
            if (arr[fastPoint] != arr[fastPoint - 1]) {
                arr[slowPoint] = arr[fastPoint];
                slowPoint++;
            }
            fastPoint++;
        }
        return slowPoint;
    }
}
