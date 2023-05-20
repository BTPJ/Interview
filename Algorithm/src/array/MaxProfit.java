package array;

/**
 * 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * 链接：<a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock">...</a>
 *
 * @author BTPJ  2023/3/13
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] nums = {7, 1, 5, 3, 6, 4, 8};
        System.out.println(MaxProfit.maxProfit(nums));
        System.out.println(MaxProfit.maxProfit2(nums));
    }

    /**
     * 一次遍历,一个变量记录最低值，一个变量记录最大利润
     *
     * @param prices 数组
     * @return 最大利润
     */
    private static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else if (price - min > max) {
                max = price - min;
            }
        }
        return max;
    }

    /**
     * 双重for循环
     *
     * @param prices 数组
     * @return 最大利润
     */
    private static int maxProfit2(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(prices[j] - prices[i], max);
            }
        }
        return max;
    }
}
