package array;

/**
 * 二分查找
 * <a href="https://leetcode-cn.com/problems/binary-search/"/>
 *
 * @author BTPJ 2021/6/9
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        System.out.println("二分查找到的下标为：" + search(nums, 5));
    }

    /**
     * 通过二分查找找出数组中值为val的下标index
     *
     * @param nums   数组
     * @param target 要查找的值
     * @return 值所在坐标，没找到返回-1
     */
    private static int search(int[] nums, int target) {
        // 左右双指针
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
