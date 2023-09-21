package tree;

import tree.base.TreeNode;

/**
 * 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 链接：<a href="https://leetcode.cn/problems/symmetric-tree/">...</a>
 *
 * @author BTPJ  2023/3/23
 */
public class IsSymmetric {

    public static void main(String[] args) {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = CreateTreeNode.array2Tree(arr);
        System.out.println("该二叉树是否是对称二叉树：" + isSymmetric(tree));
    }

    private static boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    /**
     * 判断左子树与右子树是否对称
     *
     * @param leftTree  左子树
     * @param rightTree 右子树
     * @return 是否对称
     */
    private static boolean check(TreeNode leftTree, TreeNode rightTree) {
        if (leftTree == null && rightTree == null) return true;

        if (leftTree == null || rightTree == null) return false;

        return leftTree.val == rightTree.val && check(leftTree.left, rightTree.right) && check(leftTree.right, rightTree.left);
    }
}
