package tree;

import tree.base.TreeNode;

/**
 * 平衡二叉树
 * 一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 *
 * @author BTPJ  2021/6/9
 */
public class IsBalanced {

    public static void main(String[] args) {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = CreateTreeNode.array2Tree(arr);
        System.out.println("该二叉树是否是平衡二叉树：" + isBalanced(tree));
    }

    public static boolean isBalanced(TreeNode root) {
//        if (root == null) return true;
//        return Math.abs(height1(root.left) - height1(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);

        return height2(root) >= 0;
    }

    /**
     * 自顶向下遍历，时间复杂度O(n^2)
     *
     * @param root TreeNode
     * @return 树的高度
     */
    private static int height1(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height1(root.left), height1(root.right)) + 1;
    }

    /**
     * 自底向上遍历检测，时间复杂度O(n)
     *
     * @param root TreeNode
     * @return -1表示已经不平衡，否则计算高度
     */
    private static int height2(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height2(root.left);
        int rightHeight = height2(root.right);
        if (leftHeight >= 0 && rightHeight >= 0 && Math.abs(leftHeight - rightHeight) <= 1) {
            // 这里对高度计数
            return Math.max(leftHeight, rightHeight) + 1;
        } else {
            // -1表示树已经不平衡了
            return -1;
        }
    }
}
