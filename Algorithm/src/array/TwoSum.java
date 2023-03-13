package array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * 给定一个整数数组nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 链接：<a href="https://leetcode.cn/problems/two-sum">...</a>
 *
 * @author BTPJ  2023/3/13
 */
public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum1(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(twoSum2(new int[]{3, 2, 4}, 6)));
    }

    /**
     * 哈希表方式 O(n)
     *
     * @param nums   数组
     * @param target 目标值
     * @return 结果数组
     */
    private static int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return null;
    }

    /**
     * 双重遍历方式 O(n^2)
     *
     * @param nums   数组
     * @param target 目标值
     * @return 结果数组
     */
    private static int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
