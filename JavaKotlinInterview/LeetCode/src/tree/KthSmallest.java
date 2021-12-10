package tree;

import com.btpj.leetcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 *
 * @author BTPJ  2021/6/9
 */
public class KthSmallest {

    public static void main(String[] args) {
        Integer[] arr = {3, 1, 4, null, 2};
        TreeNode tree = CreateTreeNode.array2Tree(arr);
        System.out.println("该二叉树的第2小值是：" + kthSmallest(tree,2));
    }

    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root,list);
        return list.get(k - 1);
    }

    private static void inorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
