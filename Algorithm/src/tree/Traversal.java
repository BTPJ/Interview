package tree;

import tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的遍历
 * 前序遍历： <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal/"/>
 * 中序遍历：<a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal/"/>
 * 后序遍历：<a href="https://leetcode-cn.com/problems/binary-tree-postorder-traversal/">
 * 序列化二叉树：<a href="https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/"/>
 *
 * @author BTPJ  2021/6/8
 */
public class Traversal {

    public static void main(String[] args) {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = CreateTreeNode.array2Tree(arr);

        StringBuilder sb = new StringBuilder();
        List<Integer> preorderList = preorderTransversal(tree);
        sb.append("前序遍历：");
        preorderList.forEach(it -> sb.append(it).append(","));
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);

        List<Integer> inorderList = inorderTransversal(tree);
        sb.delete(0, sb.length());
        sb.append("中序遍历：");
        inorderList.forEach(it -> sb.append(it).append(","));
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);

        List<Integer> postorderList = postorderTransversal(tree);
        sb.delete(0, sb.length());
        sb.append("后序遍历：");
        postorderList.forEach(it -> sb.append(it).append(","));
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    /**
     * 前序遍历（根->左->右）：先访问根结点，然后前序遍历左子树，再前序遍历右子树
     *
     * @param root 要遍历的二叉树
     * @return 返回的集合
     */
    public static List<Integer> preorderTransversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    /**
     * 前序递归
     *
     * @param root TreeNode
     * @param list  List
     */
    private static void preorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /**
     * 中序遍历（左->根->右）：先中序遍历根节点的左子树，然后访问根结点，最后中序遍历右子树
     *
     * @param root 要遍历的二叉树
     * @return 返回的集合
     */
    public static List<Integer> inorderTransversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    /**
     * 中序递归
     *
     * @param root TreeNode
     * @param list  List
     */
    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 后序遍历（左->右->根）
     *
     * @param root 要遍历的二叉树
     * @return 返回的集合
     */
    public static List<Integer> postorderTransversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    /**
     * 后序递归
     *
     * @param root TreeNode
     * @param list  List
     */
    private static void postorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }
}
