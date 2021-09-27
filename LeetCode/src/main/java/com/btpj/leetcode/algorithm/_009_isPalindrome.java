package com.btpj.leetcode.algorithm;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1: 输入: 121 输出: true
 * <p>
 * 示例 2: 输入: -121 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3: 输入: 10 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 *
 * @author BTPJ  2020/11/24
 */
public class _009_isPalindrome {

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;

        int rem, y = 0;
        int tempNum = x;
        while (tempNum != 0) {
            rem = tempNum % 10;
            tempNum = tempNum / 10;
            y = y * 10 + rem;
        }
        return y == x;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(Integer.MAX_VALUE));

    }
}
