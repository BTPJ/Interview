package com.btpj.leetcode.algorithm;

import java.util.Arrays;

/**
 * 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 * @author BTPJ  2020/11/10
 */
public class _004_findMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1Length = nums1.length;
        int num2Length = nums2.length;
        int[] nums = Arrays.copyOf(nums1, num1Length + num2Length);
        System.arraycopy(nums2, 0, nums, num1Length, num2Length);
        int[] sortNums = Arrays.stream(nums).sorted().toArray();

        if ((num1Length + num2Length) % 2 == 0) {
            return (sortNums[(num1Length + num2Length - 1) / 2] + sortNums[(num1Length + num2Length + 1) / 2]) / 2.0;
        } else {
            return sortNums[(num1Length + num2Length) / 2];
        }
    }

    public static void main(String[] args) {
        double medianNum = findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4, 5});
        System.out.println(medianNum);
    }
}
