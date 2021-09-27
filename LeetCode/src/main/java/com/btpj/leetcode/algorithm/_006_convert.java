package com.btpj.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING"行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows =4
 * 输出:"LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * <p>
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 *
 * @author BTPJ  2020/11/11
 */
public class _006_convert {

    public static String convert(String s, int numRows) {
        if (numRows < 2) return s;

        List<StringBuilder> rows = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        for (int j = 0; j < arr.length; j++) {
            int position = j % (numRows + (numRows - 2));
            if (position < numRows) {
                rows.get(position).append(arr[j]);
            } else {
                rows.get(numRows - (position - numRows) - 2).append(arr[j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 4));
    }
}
