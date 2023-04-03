package string;

/**
 *  二进制求和
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * 链接：<a href="https://leetcode.cn/problems/add-binary/">...</a>
 *
 * @author BTPJ  2023/4/3
 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "11", b = "1";
        System.out.println(addBinary(a, b));
    }

    private static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            sb.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }
}
