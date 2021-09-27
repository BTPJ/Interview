package com.btpj.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 罗马数字包含以下七种字符:  I，  V，  X，  L，C，D  和  M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做  II  ，即为两个并列的 1。12 写做  XII  ，即为  X  +  II  。 27 写做    XXVII, 即为  XX  +  V  +  II  。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做  IIII，而是  IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为  IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I  可以放在  V  (5) 和  X  (10) 的左边，来表示 4 和 9。
 * X  可以放在  L  (50) 和  C  (100) 的左边，来表示 40 和  90。
 * C  可以放在  D  (500) 和  M  (1000) 的左边，来表示  400 和  900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1  到 3999 的范围内。
 * <p>
 * 示例  1: 输入:  "III" 输出: 3
 * 示例  2: 输入:  "IV" 输出: 4
 * 示例  3: 输入:  "IX" 输出: 9
 * 示例  4: 输入:  "LVIII" 输出: 58 解释: L = 50, V= 5, III = 3.
 * 示例  5: 输入:  "MCMXCIV" 输出: 1994 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 *
 * @author BTPJ  2020/11/27
 */
public class _013_romanToInt {

    public static int romanToInt(String s) {
        List<String> indexList = new ArrayList<>();
        List<String> romanList = new ArrayList<>();
        char[] sArray = s.toCharArray();
        char[] roman = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        for (char c : sArray) {
            for (int i = 0; i < roman.length; i++) {
                if (c == roman[i]) {
                    indexList.add(i + "");
                }
            }
        }

        for (int i = 0; i < indexList.size(); i++) {
            if (i + 1 < indexList.size() && Integer.parseInt(indexList.get(i)) > Integer.parseInt(indexList.get(i + 1))) {
                romanList.add(indexList.get(i) + indexList.get(i + 1));
                i++;
            } else {
                romanList.add(indexList.get(i));
            }
        }

        int result = 0;
        for (String s1 : romanList) {
            switch (s1) {
                case "0":
                    result += 1000;
                    break;
                case "20":
                    result += 900;
                    break;
                case "1":
                    result += 500;
                    break;
                case "21":
                    result += 400;
                    break;
                case "2":
                    result += 100;
                    break;
                case "42":
                    result += 90;
                    break;
                case "3":
                    result += 50;
                    break;
                case "43":
                    result += 40;
                    break;
                case "4":
                    result += 10;
                    break;
                case "64":
                    result += 9;
                    break;
                case "5":
                    result += 5;
                    break;
                case "65":
                    result += 4;
                    break;
                case "6":
                    result += 1;
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }
}
