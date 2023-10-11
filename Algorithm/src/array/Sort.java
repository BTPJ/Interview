package array;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序算法
 *
 * @author BTPJ 2021/5/17
 */
public class Sort {
    private static final int[] arr = { 12, 13, 4, 7, 2, 21, 8 };

    public static void main(String[] args) {
        // 快速排序
        quickSort(arr, 0, arr.length - 1);

        // 插入排序
        // insertSort(arr);

        // 冒泡排序
        // maoPaoSort(arr);

        // 选择排序
        // chooseSort(arr);

        Arrays.sort(arr);

        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

    /**
     * 冒泡排序
     * 比较相邻的元素，如果第一个比第二大，交换
     * 对每一对相邻元素重复相同的操作
     *
     * @param arr 要排序的数组
     */
    private static void maoPaoSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 依次找到最小、第二小、第三小......的元素放置在第1、2、3......位上面
     *
     * @param arr 要排序的数组
     */
    private static void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序O(n^2)
     * 左边表示已排序右边表示未排序，将右边的数移到左边去比较
     *
     * @param arr 要排序的数组
     */
    private static void insertSort(int[] arr) {
        // 从1开始，毕竟第一个元素自己本身无需排
        // 外循环：已排序元素数量为1，2，3，...，n
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i], j = i - 1; // temp作为要插入的元素
            // 内循环：将temp插入到已排序部分的正确位置
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp; // 将temp赋值到正确的位置
        }
    }

    /**
     * https://www.hello-algo.com/chapter_sorting/quick_sort/
     * 快速排序：通过划分将待排序的序列分成前后两部分，其中前一部分的数据都比后一部分的数据要小，
     * 然后再递归调用函数对两部分的序列分别进行快速排序，以此使整个序列达到有序
     * 1：选定Pivot中心轴
     * 2：将大于Pivot的数字放在Pivot的右边
     * 3：将小于Pivot的数字放在Pivot的左边
     * 4：分别对左右子序列重复前三步操作
     * O(nlogn)
     *
     * @param arr   数组
     * @param left  最左位置
     * @param right 最右位置
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right)  return; // 子数组长度为 1 时终止递归
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    /**
     * 将数组根据pivot值进行分类，比pivot小的在左侧，比pivot大的在右侧
     *
     * @param arr   数组
     * @param left  最左位置
     * @param right 最右位置
     * @return pivot的新位置
     */
    private static int partition(int[] arr, int left, int right) {
        // 随机选择一个元素作为基准元素,防止本身是逆序数组时退化成O(n^2)
        int pivotIndex = new Random().nextInt(right - left + 1) + left;
        // 将基准元素交换到序列最左端(以arr[left] 作为基准数)
        swap(arr, pivotIndex, left);

        int i = left, j = right;
        while (i < j) {
            // 注意，当基准元素在最左侧时需从右开始寻找，基准元素在最右侧时需从左开始遍历
            while (i < j && arr[j] >= arr[left]) j--; // 从右向左找首个小于基准数的元素
            while (i < j && arr[i] <= arr[left]) i++; // 从左向右找首个大于基准数的元素
            swap(arr, i, j);
        }
        swap(arr, left, i); // 最后调换基准元素位置到左右节点重合的位置
        return i;
    }

    /**
     * 调换数组中i和j下标的元素
     *
     * @param arr 数组
     * @param i   下标
     * @param j   下标
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
