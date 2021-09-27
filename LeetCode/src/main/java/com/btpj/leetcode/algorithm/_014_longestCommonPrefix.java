package com.btpj.leetcode.algorithm;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串  ""。
 * <p>
 * 示例  1: 输入: ["flower","flow","flight"] 输出: "fl"
 * 示例  2: 输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
 * 说明: 所有输入只包含小写字母  a-z  。
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 *
 * @author BTPJ  2020/11/27
 */
public class _014_longestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        char[] chars = strs[0].toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (String str : strs) {
                try {
                    if (str.charAt(i) != chars[i]) {
                        return sb.toString();
                    }
                } catch (Exception e) {
                    return sb.toString();
                }
            }
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }
}
