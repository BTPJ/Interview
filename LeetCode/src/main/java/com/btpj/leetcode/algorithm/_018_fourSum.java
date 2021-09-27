package com.btpj.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含  n 个整数的数组  nums  和一个目标值  target，判断  nums  中是否存在四个元素 a，b，c  和 d  ，
 * 使得  a + b + c + d  的值与  target  相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 * <p>
 * 示例：给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * 链接：https://leetcode-cn.com/problems/4sum
 *
 * @author BTPJ  2020/12/3
 */
public class _018_fourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums.length < 4) return resultList;

        Arrays.sort(nums);

        for (int i1 = 0; i1 < nums.length; i1++) {
            if (nums[i1] > 0 && nums[i1] > target) break;
            for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                if (nums[i2] > 0 && nums[i1] + nums[i2] > target) break;
                for (int i3 = i2 + 1; i3 < nums.length; i3++) {
                    if (nums[i3] > 0 && nums[i1] + nums[i2] + nums[i3] > target) break;
                    for (int i4 = i3 + 1; i4 < nums.length; i4++) {
                        if (nums[i1] + nums[i2] + nums[i3] + nums[i4] == target) {
                            List<Integer> list = Arrays.asList(nums[i1], nums[i2], nums[i3], nums[i4]);
                            if (resultList.isEmpty()) {
                                resultList.add(list);
                            } else {
                                for (int i = 0; i < resultList.size(); i++) {
                                    if (isSame(list, resultList.get(i))) {
                                        break;
                                    }
                                    if (i == resultList.size() - 1) {
                                        resultList.add(list);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return resultList;
    }

    private static boolean isSame(List<Integer> list1, List<Integer> list2) {
        int sameCount = 0;
        Object[] array1 = list1.toArray();
        Object[] array2 = list2.toArray();
        for (int i = 0; i < array1.length; i++) {
            if (array1[i].equals(array2[i])) {
                sameCount++;
            }
        }
        return sameCount == 4;
    }

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }
}
