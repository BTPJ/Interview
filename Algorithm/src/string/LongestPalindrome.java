package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长回文串
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串
 * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
 * 链接：<a href="https://leetcode.cn/problems/longest-palindrome/">...</a>
 *
 * @author BTPJ  2023/4/12
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("aaaaaccc"));
    }

    private static int longestPalindrome(String s) {
        int result = 1;
        // 先用hashMap存储每个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                int value = map.get(c);
                map.put(c, value + 1);
            } else {
                map.put(c, 1);
            }
        }

        // 遍历map,只有次数大于1时才可组成对称回文（假如长度为基数，最中间可以为次数为1的字符）
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                // 如果次数为偶数则均当作回文串，如果为偶数得-1
                result += entry.getValue() % 2 == 0 ? entry.getValue() : entry.getValue() - 1;
            }
        }

        // 注意取长度与result的较小者，去除整个串所有字母都是偶数的情况，例如“aabb”、“aa”
        return Math.min(result, s.length());
    }
}
