package com.btpj.leetcode.algorithm;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'  的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例  1: 输入: "()"     输出: true
 * 示例  2: 输入: "()[]{}" 输出: true
 * 示例  3: 输入: "(]"     输出: false
 * 示例  4: 输入: "([)]"   输出: false
 * 示例  5: 输入: "{[]}"   输出: true
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 *
 * @author BTPJ  2020/12/3
 */
public class _020_isValid {

    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 != 0) return false;

        int t = s.length() / 2;
        while (t > 0) {
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
            t--;
        }

        return s.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("()[]{}"));
    }
}
