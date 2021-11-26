package com.btpj.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 求两个数组的交集（第二遍刷）
 *
 * @author LTP  2021/11/15
 */
public class Intersection2 {

    public static void main(String[] args) {
        // 未排序
        int[] num1 = {4, 9, 5};
        int[] num2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(getIntersection1(num1, num2)));

        // 已排序
        int[] num3 = {4, 5, 7, 8, 8};
        int[] num4 = {4, 7, 8, 8, 9, 10};
        System.out.println(Arrays.toString(getIntersection2(num3, num4)));
    }

    /**
     * 求两个数组的交集
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 数组的交集
     */
    private static int[] getIntersection1(int[] array1, int[] array2) {
        if (array1 == null || array1.length == 0 || array2 == null || array2.length == 0) {
            return null;
        }

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i : array1) {
            set1.add(i);
        }

        for (int i : array2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }

        return set2.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 求两个有序数组的交集
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 有序数组的交集
     */
    private static int[] getIntersection2(int[] array1, int[] array2) {
        if (array1 == null || array1.length == 0 || array2 == null || array2.length == 0) {
            return null;
        }

        int index1 = 0, index2 = 0;
        Set<Integer> set = new HashSet<>();
        while (index1 < array1.length && index2 < array2.length) {
            if (array1[index1] < array2[index2]) {
                index1++;
            } else if (array1[index1] == array2[index2]) {
                set.add(array1[index1]);
                index1++;
                index2++;
            } else {
                index2++;
            }
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
