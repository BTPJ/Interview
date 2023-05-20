package array;

import java.util.*;

/**
 * 求两个数组的交集
 * 求两个已排序数组的交集
 * <a href="https://leetcode-cn.com/problems/intersection-of-two-arrays/"/>
 *
 * @author LTP  2021/11/15
 */
public class Intersection {

    public static void main(String[] args) {
        // 未排序
        int[] num1 = {4, 9, 5};
        int[] num2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(getIntersection1(num1, num2)));
        System.out.println(Arrays.toString(getIntersection2(num1, num2)));

        // 已排序
        int[] num3 = {4, 5, 7, 8, 8};
        int[] num4 = {4, 7, 8, 8, 9, 10};
        System.out.println(Arrays.toString(getIntersection3(num3, num4)));
    }

    /**
     * 求两个数组的交集
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 数组的交集
     */
    private static int[] getIntersection1(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>(nums1.length);
        Set<Integer> resultSet = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                resultSet.add(i);
            }
        }
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 求两个数组的交集(先排序再一次遍历)
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 数组的交集
     */
    private static int[] getIntersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return getIntersection3(nums1, nums2);
    }

    /**
     * 求两个有序数组的交集
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 有序数组的交集
     */
    private static int[] getIntersection3(int[] nums1, int[] nums2) {
        Set<Integer> resultSet = new HashSet<>();
        // 分别指向nums1、nums2的双指针
        int nums1Point = 0, nums2Point = 0;
        while (nums1Point < nums1.length && nums2Point < nums2.length) {
            if (nums1[nums1Point] == nums2[nums2Point]) {
                resultSet.add(nums1[nums1Point]);
                nums1Point++;
                nums2Point++;
            } else if (nums1[nums1Point] > nums2[nums2Point]) {
                nums2Point++;
            } else {
                nums1Point++;
            }
        }
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }
}
