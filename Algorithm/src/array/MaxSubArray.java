package array;

/**
 * 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 * <a href="https://leetcode.cn/problems/maximum-subarray/">...</a>]
 *
 * @author BTPJ 2023/3/10
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(MaxSubArray.maxSubArray2(nums));
    }

    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int preSum = 0;
        int maxSum = nums[0];
        for (int num : nums) {
            if (preSum < 0) {
                preSum = num;
            } else {
                preSum += num;
            }
            maxSum = Math.max(preSum, maxSum);
        }
        return maxSum;
    }

    /**
     * https://leetcode.cn/problems/maximum-subarray/solutions/2361770/53-zui-da-zi-shu-zu-he-dong-tai-gui-hua-bvkq9/
     * 
     * @param nums
     * @return
     */
    private static int maxSubArray2(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 每次将当前位置的元素置为之前子序列的最大值
            nums[i] += Math.max(nums[i - 1], 0);
            // 寻找较大值，赋值给result
            result = Math.max(result, nums[i]);
        }

        return result;
    }
}
