package dp;

/**
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 链接：<a href="https://leetcode.cn/problems/climbing-stairs/">...</a>
 *
 * @author BTPJ  2023/4/14
 */
public class ClimbStairs {

    public static void main(String[] args) {
//        System.out.println(climbStairs(44));
        System.out.println(climbStairs2(44));
        System.out.println(climbStairs3(44));
    }

    /**
     * 递归法
     *
     * @param n n
     * @return 结果
     */
    private static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 等于倒数第二级楼梯再上2阶，和倒数第一级楼梯再上1阶
        return climbStairs(n - 2) + climbStairs(n - 1);
    }

    /**
     * 递归速度优化（动态规划）
     * 由于递归中每一个值并没有缓存会算多遍，这里使用数组缓存
     *
     * @param n n
     * @return 结果
     */
    private static int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }

    /**
     * 递归空间再次优化（滚动数组）
     *
     * @param n n
     * @return 结果
     */
    private static int climbStairs3(int n) {
        int arrLeft = 0, arrCenter = 0, arrRight = 1;
        for (int i = 1; i <= n; i++) {
            arrLeft = arrCenter;
            arrCenter = arrRight;
            arrRight = arrLeft + arrCenter;
        }

        return arrRight;
    }
}
