package tree;


import tree.base.TreeNode;

/**
 * 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
 * 链接：<a href="https://leetcode.cn/problems/invert-binary-tree/">...</a>
 *
 * @author BTPJ  2023/3/23
 */
public class InvertTree {

    public static void main(String[] args) {
    }

    private static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode leftTree = invertTree(root.right);
        TreeNode rightTree = invertTree(root.left);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }
}
