package string;

/**
 * 判断子序列
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）
 * 链接：<a href="https://leetcode.cn/problems/is-subsequence></a>
 *
 * @author BTPJ  2023/4/18
 */
public class IsSubsequence {

    public static void main(String[] args) {
        System.out.println(isSubsequence("b", "abc"));
    }

    /**
     * 采用双指针的方式
     *
     * @param s 字串
     * @param t 完整串
     * @return 字串是否是完整串的子序列
     */
    private static boolean isSubsequence(String s, String t) {
        int sPoint = 0, tPoint = 0;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        while (sPoint < sArr.length && tPoint < tArr.length) {
            if (tArr[tPoint] == sArr[sPoint]) {
                sPoint++;
            }
            tPoint++;
        }

        return sPoint == sArr.length;
    }
}
