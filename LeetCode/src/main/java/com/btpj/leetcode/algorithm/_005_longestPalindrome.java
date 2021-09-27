package com.btpj.leetcode.algorithm;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设s 的最大长度为 1000。
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 *
 * @author BTPJ  2020/11/11
 */
public class _005_longestPalindrome {

    public static String longestPalindrome(String s) {
        char[] charArr = s.toCharArray();
        StringBuilder sb = new StringBuilder(s.substring(0,1));
        for (int i = 0; i < charArr.length; i++) {
            StringBuilder sbTemp = new StringBuilder();
            sbTemp.append(charArr[i]);
            for (int j = i + 1; j < charArr.length; j++) {
                sbTemp.append(charArr[j]);
                if (charArr[i] == charArr[j]) {
                    if (checkPalindrome(sbTemp.toString())) {
                        if (sb.length() < sbTemp.length()) {
                            sb = new StringBuilder(sbTemp);
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 检测str 是否是回文串
     *
     * @param str 字符串
     * @return 是否是回文串
     */
    private static boolean checkPalindrome(String str) {
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (i >= charArr.length / 2) {
                return true;
            }
            if (charArr[i] != charArr[charArr.length - i - 1]) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String longestPalindrome = longestPalindrome("babad");
        System.out.println(longestPalindrome);
    }

}
