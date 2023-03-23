package tree;

import tree.base.TreeNode;

/**
 * 求二叉树的最大深度
 * <a href="https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/"/>
 *
 * @author BTPJ  2021/6/8
 */
public class MaxDepth {

    public static void main(String[] args) {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = CreateTreeNode.array2Tree(arr);
        System.out.println("二叉树的最大深度：" + maxDepth(tree));
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        // 左右子树的最大深度中的较大者+根结点
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
