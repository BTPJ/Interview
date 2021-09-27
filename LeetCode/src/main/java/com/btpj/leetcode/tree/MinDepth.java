package com.btpj.leetcode.tree;

import com.btpj.leetcode.tree.base.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * @author BTPJ  2021/6/9
 */
public class MinDepth {

    public static void main(String[] args) {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = CreateTreeNode.array2Tree(arr);
        System.out.println("二叉树的最小深度：" + minDepth(tree));
    }

    private static int minDepth(TreeNode root) {
        if (root == null) return 0;

        // 存放左右子树最小高度，故赋值为最大int，使得比较之后能替换成较小值
        int minDepth = Integer.MAX_VALUE;

        if (minDepth(root.left) < minDepth) {
            minDepth = minDepth(root.left);
        }

        if (minDepth(root.right) < minDepth) {
            minDepth = minDepth(root.right);
        }

        // 左右子树的最小深度 + 根结点
        return minDepth + 1;
    }
}
