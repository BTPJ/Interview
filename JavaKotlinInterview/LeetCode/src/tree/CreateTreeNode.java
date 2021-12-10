package tree;

import com.btpj.leetcode.tree.base.TreeNode;

import java.util.*;

/**
 * 二叉树的创建
 * 从前序与中序遍历序列构造二叉树：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 从中序与后序遍历序列构造二叉树：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 从前序与后序遍历序列构造二叉树：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * @author BTPJ  2021/6/8
 */
public class CreateTreeNode {

    /**
     * 序列化转成二叉树
     *
     * @param arr 数组
     * @return 二叉树
     */
    public static TreeNode array2Tree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};
        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode node = queue.poll();
            if (arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                queue.add(node.left);
            }
            i++;
            if (arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }


    /**
     * 根据前序和中序序列构造二叉树
     *
     * @param preorder 前序序列
     * @param inorder  中序序列
     * @return 构造的二叉树
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            assert node != null;
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
