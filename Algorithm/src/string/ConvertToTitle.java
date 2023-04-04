package string;

/**
 * Excel表列名称
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称
 * 例如：
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 链接：<a href="https://leetcode.cn/problems/excel-sheet-column-title/">...</a>
 *
 * @author BTPJ  2023/4/4
 */
public class ConvertToTitle {
    public static void main(String[] args) {
        System.out.println(convertToTitle(2147483647));
    }

    /**
     * 26进制变种
     *
     * @param columnNumber 数字
     * @return 结果
     */
    private static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            // 每次计算之前先-1是由于普通进制是从0开始的，而这个"进制"是从1开始的所以需要-1
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber = columnNumber / 26;
        }
        sb.reverse();
        return sb.toString();
    }
}
