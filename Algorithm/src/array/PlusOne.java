package array;

import java.util.Arrays;

/**
 * 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * link: <a href="https://leetcode-cn.com/problems/plus-one/">...</a>
 *
 * @author BTPJ  2023/3/30
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] arr = {9, 9, 9};
        System.out.println(Arrays.toString(plusOne(arr)));
    }

    /***
     * 数组法
     * @param digits 数组
     * @return 结果
     */
    private static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }

        // digits中所有元素均为9
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
