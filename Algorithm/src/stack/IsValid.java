package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 链接：<a href="https://leetcode.cn/problems/valid-parentheses/?envType=study-plan&id=shu-ju-jie-gou-ru-men&plan=data-structures&plan_progress=xhd088o3">...</a>
 *
 * @author LTP
 */
public class IsValid {

    public static void main(String[] args) {
        String s = "()[]{}";
        String s2 = "(]";
        System.out.println(s + ":" + (isValid(s) ? "合法" : "不合法"));
        System.out.println(s2 + ":" + (isValid2(s2) ? "合法" : "不合法"));
    }

    /**
     * 方式1 swich-case/if-else
     *
     * @param s s
     * @return 是否合法
     */
    private static boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(')');
                    break;

                case '{':
                    stack.push('}');
                    break;

                case '[':
                    stack.push(']');
                    break;

                default:
                    if (stack.empty() || c != stack.pop()) return false;
                    break;
            }
        }
        return stack.empty();
    }

    /**
     * 方式2 HashMap
     *
     * @param s s
     * @return 是否合法
     */
    private static boolean isValid2(String s) {
        if (s.length() % 2 != 0) return false;
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.empty();
    }
}
