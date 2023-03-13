package array;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 给你两个递增顺序排列的整数数组nums1 和 nums2
 * 请你合并 nums1 和 nums2，使合并后的数组同样按 非递减顺序 排列。
 * 链接：<a href="https://leetcode.cn/problems/merge-sorted-array">...</a>
 *
 * @author BTPJ  2023/3/13
 */
public class Merge {
    public static void main(String[] args) {
//        int[] nums1 = {2, 0};
//        int[] nums2 = {1};
//        merge(nums1, 1, nums2, 1);
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);


        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = 0;
        int nums2Index = 0;
        int[] result = new int[m + n];
        int resultIndex = 0;
        while (nums1Index < m || nums2Index < n) {
            if (nums1Index == m) {
                result[resultIndex] = nums2[nums2Index];
                nums2Index++;
            } else if (nums2Index == n) {
                result[resultIndex] = nums1[nums1Index];
                nums1Index++;
            } else if (nums1[nums1Index] < nums2[nums2Index]) {
                result[resultIndex] = nums1[nums1Index];
                nums1Index++;
            } else {
                result[resultIndex] = nums2[nums2Index];
                nums2Index++;
            }
            resultIndex++;
        }

        System.arraycopy(result, 0, nums1, 0, result.length);
    }
}
