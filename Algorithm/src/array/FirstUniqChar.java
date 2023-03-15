package array;

import java.util.*;

/**
 * 字符串中的第一个唯一字符
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1
 * 链接：<a href="https://leetcode.cn/problems/first-unique-character-in-a-string/?envType=study-plan&id=shu-ju-jie-gou-ru-men&plan=data-structures&plan_progress=xhd088o3">...</a>
 *
 * @author BTPJ  2023/3/15
 */
public class FirstUniqChar {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
        System.out.println(firstUniqChar2("loveleetcode"));
    }

    /**
     * 方式1：使用hashMap
     *
     * @param s 字符串
     * @return 结果index
     */
    private static int firstUniqChar(String s) {
        if (s == null) return -1;

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方式2：使用Set和List
     *
     * @param s 字符串
     * @return 结果index
     */
    private static int firstUniqChar2(String s) {
        if (s == null) return -1;

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(String.valueOf(c))) {
                list.remove(String.valueOf(c));
            } else {
                list.add(String.valueOf(c));
                set.add(String.valueOf(c));
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (list.contains(String.valueOf(s.charAt(i)))) {
                return i;
            }
        }
        return -1;
    }
}
