package array;

/**
 * 二分查找
 * https://leetcode-cn.com/problems/binary-search/
 *
 * @author BTPJ  2021/6/9
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println("二分查找到的下标为：" + search(nums, 3));
    }

    /**
     * 通过二分查找找出数组中值为val的下标index
     *
     * @param nums   数组
     * @param target 要查找的值
     * @return 值所在坐标，没找到返回-1
     */
    private static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int index = left + (right - left) / 2;
            if (nums[index] == target) return index;
            if (nums[index] > target) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return -1;
    }
}
