package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 链接：<a href="https://leetcode.cn/problems/pascals-triangle/?envType=study-plan&id=shu-ju-jie-gou-ru-men&plan=data-structures&plan_progress=xhd088o3">...</a>
 *
 * @author BTPJ  2023/3/13
 */
public class Generate {
    public static void main(String[] args) {
        System.out.println(generate1(5));
//        System.out.println(generate2(14));
    }

    /**
     * 遍历方案（更优）
     *
     * @param numRows numRows
     * @return List
     */
    private static List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> list = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    List<Integer> top = list.get(i - 1);
                    row.add(top.get(j - 1) + top.get(j));
                }
            }
            list.add(row);
        }
        return list;
    }

    /**
     * 递归方案
     *
     * @param numRows numRows
     * @return List
     */
    private static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> list = new ArrayList<>(numRows);
        for (int i = 1; i <= numRows; i++) {
            list.add(Arrays.stream(generateArr(i)).boxed().collect(Collectors.toList()));
        }
        return list;
    }

    private static int[] generateArr(int numRows) {
        if (numRows == 1) return new int[]{1};

        int[] arr = new int[numRows];
        arr[0] = 1;
        arr[numRows - 1] = 1;
        for (int i = 1; i < numRows - 1; i++) {
            int[] topArr = generateArr(numRows - 1);
            arr[i] = topArr[i - 1] + topArr[i];
        }
        return arr;
    }
}
