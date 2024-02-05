package tree;

import tree.base.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度（最小深度是从根节点到最近叶子节点的最短路径上的节点数量。）
 * <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/"/>
 *
 * @author BTPJ 2021/6/9
 */
public class MinDepth {

    public static void main(String[] args) {
        Integer[] arr = { 2, null, 3, null, 4, null, 5, null, 6 };
        TreeNode tree = CreateTreeNode.array2Tree(arr);
        System.out.println("二叉树的最小深度：" + minDepth(tree));
    }

    private static int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        int leftMin = minDepth(root.left);
        int rightMin = minDepth(root.right);

        // 左子节点为空时，取右测节点最小 + 1 
        if (root.left ==null) return rightMin + 1;

        //  右子节点为空时，取左侧节点最小 + 1 
        if (root.right ==null) return leftMin + 1;

        // 加上根节点
        return Math.min(leftMin, rightMin) + 1;
    }
}
