package com.btpj.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 求两个数组的交集
 * 求两个已排序数组的交集
 *
 * @author LTP  2021/11/9
 */
public class Intersection {
    /**
     * 对未排序的两个数组求交集，复杂度O(m+n)
     *
     * @param num1 数组1
     * @param num2 数组2
     * @return 交集数组
     */
    private static int[] getNum1(int[] num1, int[] num2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : num1) {
            set1.add(i);
        }

        for (int i : num2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }
        return set2.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 对已排序的两个数组求交集，复杂度O(max(m,n))
     *
     * @param num1 数组1
     * @param num2 数组2
     * @return 交集数组
     */
    private static int[] getNum2(int[] num1, int[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;

        int index = 0, index1 = 0, index2 = 0;
        int[] resultNum = new int[len1 + len2];
        while (index1 < len1 && index2 < len2) {
            int n1 = num1[index1], n2 = num2[index2];
            if (n1 == n2) {
                if (index == 0 || n1 != resultNum[index - 1]) {
                    resultNum[index++] = n1;
                }
                index1++;
                index2++;
            } else if (n1 > n2) {
                index2++;
            } else {
                index1++;
            }
        }
        return Arrays.copyOf(resultNum, index);
    }

    public static void main(String[] args) {
        // 未排序
        int[] num1 = {4, 9, 5};
        int[] num2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(getNum1(num1, num2)));

        // 已排序
        int[] num3 = {4, 5, 7};
        int[] num4 = {4, 7, 8, 8, 9, 10};
        System.out.println(Arrays.toString(getNum2(num3, num4)));
    }
}
