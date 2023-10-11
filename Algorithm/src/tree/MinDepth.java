package tree;

import tree.base.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度
 * <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/"/>
 *
 * @author BTPJ  2021/6/9
 */
public class MinDepth {

    public static void main(String[] args) {
        Integer[] arr = {2,null,3,null,4,null,5,null,6};
        TreeNode tree = CreateTreeNode.array2Tree(arr);
        System.out.println("二叉树的最小深度：" + minDepth(tree));
    }

    private static int minDepth(TreeNode root) {
        if (root == null) return 0;

        if(root.left == null && root.right == null) return 1;
        
        // 存放左右子树最小高度，故赋值为最大int，使得比较之后能替换成较小值
        int minDepth = Integer.MAX_VALUE;
        if(root.left != null){ 
           minDepth = Math.min(minDepth(root.left), minDepth);
        }
        
         if(root.right != null){ 
           minDepth = Math.min(minDepth(root.right), minDepth);
        }

        return minDepth + 1;
    }
}
