package com.btpj.leetcode.algorithm;

/**
 * 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转
 * 示例 1: 输入: 123 输出: 321
 * 示例 2: 输入: -123 输出: -321
 * 示例 3: 输入: 120 输出: 21
 * 注意:假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为[−2^31, 2^31− 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 *
 * @author BTPJ  2020/10/29
 */
public class _007_reverseInteger {
    public static int reverse(int x) {
//        int x2;
//        if (x < 0) {
//            x2 = x * (-1);
//        } else {
//            x2 = x;
//        }
//        StringBuilder xStr = new StringBuilder(String.valueOf(x2));
//        xStr.reverse();
//        try {
//            if (x < 0) {
//                return Integer.parseInt(xStr.toString()) * -1;
//            } else {
//                return Integer.parseInt(xStr.toString());
//            }
//        }catch (Exception e){
//            return 0;
//        }
        int res = 0;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(134));
    }
}
