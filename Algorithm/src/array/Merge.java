package array;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 给你两个递增顺序排列的整数数组nums1 和 nums2。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列
 * 链接：<a href="https://leetcode.cn/problems/merge-sorted-array">...</a>
 *
 * @author BTPJ  2023/3/13
 */
public class Merge {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 5, 6};

        System.out.println(Arrays.toString(merge(nums1, nums2)));
    }

    /**
     * 每个数组一个指针一次遍历实现
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 实际结果
     */
    public static int[] merge(int[] nums1, int[] nums2) {
        int nums1Point = 0, nums2Point = 0, resultPoint = 0;
        int[] result = new int[nums1.length + nums2.length];
        while (nums1Point < nums1.length || nums2Point < nums2.length) {
            if (nums1Point == nums1.length) {
                result[resultPoint] = nums2[nums2Point];
                nums2Point++;
                resultPoint++;
            } else if (nums2Point == nums2.length) {
                result[resultPoint] = nums1[nums1Point];
                nums2Point++;
                resultPoint++;
            } else {
                if (nums1[nums1Point] < nums2[nums2Point]) {
                    result[resultPoint] = nums1[nums1Point];
                    nums1Point++;
                    resultPoint++;
                } else if (nums1[nums1Point] > nums2[nums2Point]) {
                    result[resultPoint] = nums2[nums2Point];
                    nums2Point++;
                    resultPoint++;
                } else {
                    result[resultPoint] = nums1[nums1Point];
                    nums1Point++;
                    resultPoint++;
                    result[resultPoint] = nums2[nums2Point];
                    nums2Point++;
                    resultPoint++;
                }
            }
        }

        return result;
    }
}
