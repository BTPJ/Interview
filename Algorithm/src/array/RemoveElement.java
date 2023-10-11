package array;

import java.util.Arrays;

/**
 * 原地删除（空间复杂度O(1)）有序数组中的指定元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度
 * 链接：<a href="https://leetcode.cn/problems/remove-element/">...</a>
 *
 * @author BTPJ  2023/3/28
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] arr = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement(arr,2));
        // System.out.println(Arrays.toString(removeElement(arr,2)));
    }

    /**
     * 双指针法
     *
     * @param nums 数组
     * @param val  指定元素
     * @return 数组的长度
     */
    private static int removeElement(int[] nums,int val) {
        int slowPoint = 0, fastPoint = 0;
        while (fastPoint < nums.length) {
            if (nums[fastPoint] != val) {
                nums[slowPoint] = nums[fastPoint];
                slowPoint++;
            }
            fastPoint++;
        }
        return slowPoint;
    }
}
