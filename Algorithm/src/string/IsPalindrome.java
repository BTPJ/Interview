package string;

/**
 * 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 链接：<a href="https://leetcode.cn/problems/valid-palindrome">...</a>
 *
 * @author BTPJ  2023/4/3
 */
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));
    }

    public static boolean isPalindrome(String s) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                String str = String.valueOf(c).toLowerCase();
                sb1.append(str);
                sb2.insert(0, str);
            }
        }
        return sb1.toString().equals(sb2.toString());
    }
}
