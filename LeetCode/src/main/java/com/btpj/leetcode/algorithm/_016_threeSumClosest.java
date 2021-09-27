package com.btpj.leetcode.algorithm;

/**
 * 给定一个包括  n 个整数的数组  nums  和 一个目标值  target。找出  nums  中的三个整数，使得它们的和与  target  最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例： 输入：nums = [-1,2,1,-4], target = 1 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 *
 * @author BTPJ  2020/12/2
 */
public class _016_threeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        int result = 0;
        Integer temp = null;
        for (int i1 = 0; i1 < nums.length; i1++) {
            for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                for (int i3 = i2 + 1; i3 < nums.length; i3++) {
                    int count = nums[i1] + nums[i2] + nums[i3];
                    if (temp == null || Math.abs(temp) > Math.abs(count - target)) {
                        result = count;
                        temp = count - target;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
