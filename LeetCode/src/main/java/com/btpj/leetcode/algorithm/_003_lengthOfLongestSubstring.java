package com.btpj.leetcode.algorithm;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例1:
 * 输入: "abcabcbb" 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb" 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew" 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是子串的长度，"pwke"是一个子序列，不是子串。
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *
 * @author BTPJ  2020/11/3
 */
public class _003_lengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        // 字符串长度
        int length = chars.length;
        HashMap hashMap = new HashMap();
        // 最长子串长度
        int max = 0;
        // 每一次的最长子串长度
        int res = 0;
        // 第一层循环 数组长度 减 上次的最大长度。避免多余的无效遍历
        for (int i = 0; i < length - max; i++) {
            // 从第i个 获取最长子串长度
            for (int j = i; j < length; j++) {
                // 若重复则跳出当前循环，清空map和res
                if (hashMap.containsKey(chars[j])) {
                    hashMap.clear();
                    res = 0;
                    break;
                } else {
                    // 不重复，放入map来方便判断下一次是否重复
                    hashMap.put(chars[j], null);
                    res++;
                    max = Math.max(res, max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int len = lengthOfLongestSubstring("pwwkew");
        System.out.println(len);
    }
}
