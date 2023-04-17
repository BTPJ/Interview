package dp;

/**
 * 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和
 * 链接：<a href="https://leetcode.cn/problems/fibonacci-number/">...</a>
 *
 * @author BTPJ  2023/4/17
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(fib2(2));
    }

    /**
     * 递归法
     *
     * @param n n
     * @return 结果
     */
    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 递归速度优化（动态规划）
     * 由于递归中每一个值并没有缓存会算多遍，这里使用数组缓存
     *
     * @param n n
     * @return 结果
     */
    public static int fib2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

    /**
     * 递归空间再次优化（滚动数组）
     *
     * @param n n
     * @return 结果
     */
    public static int fib3(int n) {
        if (n == 0) return 0;

        int arrLeft = 0, arrCenter = 0, arrRight = 1;

        for (int i = 2; i <= n; i++) {
            arrLeft = arrCenter;
            arrCenter = arrRight;
            arrRight = arrLeft + arrCenter;
        }

        return arrRight;
    }
}
