package string;

/**
 * 字符串相加
 * 给定两个字符串形式的非负整数num1 和num2，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
 * 链接：<a href="https://leetcode.cn/problems/add-strings">...</a>
 *
 * @author BTPJ  2023/4/12
 */
public class AddStrings {

    public static void main(String[] args) {
//        System.out.println(addStrings("11", "123"));
//        System.out.println(addStrings("456", "77"));
        System.out.println(addStrings("9", "99"));
    }

    private static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int point1 = num1.length() - 1;
        int point2 = num2.length() - 1;
        int carry = 0;

        while (point1 >= 0 || point2 >= 0) {
            int a = point1 >= 0 ? num1.charAt(point1) - '0' : 0;
            int b = point2 >= 0 ? num2.charAt(point2) - '0' : 0;
            sb.append((a + b + carry) % 10);
            carry = (a + b + carry) / 10;
            point1--;
            point2--;
        }

        if (carry == 1) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }
}
