package tree;

import tree.base.TreeNode;

/**
 * 将有序数组转换为高度平衡的二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * 链接：ht<a href="tps://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree"></a>
 *
 * @author BTPJ  2023/3/31
 */
public class SortedArrayToBST {

    private static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    /**
     * 递归构造二叉树
     *
     * @param nums       数组
     * @param beginIndex 开始位置
     * @param endIndex   结束位置
     * @return 二叉树
     */
    private static TreeNode helper(int[] nums, int beginIndex, int endIndex) {
        if (beginIndex > endIndex) return null;

        // 二叉搜索树的中间值作为根结点，注意中间值的取值问题
        int middle = (beginIndex + endIndex) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        // 左子树是由数组的前半部分（beginIndex~中间值的前一个位置）构造而成的
        root.left = helper(nums, beginIndex, middle - 1);
        // 右子树是由数组的后半部分（中间值的后一个位置~endIndex）构造而成的
        root.right = helper(nums, middle + 1, endIndex);
        return root;
    }
}
