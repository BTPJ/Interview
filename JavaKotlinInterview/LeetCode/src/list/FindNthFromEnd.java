package list;

import list.base.ListNode;

/**
 * 使用一次循环寻找链表的倒数第 k个结点
 * <p>
 * 给定一个链表，如何使用一次循环获取链表的倒数第 k个节点
 * 示例：给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 结果 result = 4
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 *
 * @author BTPJ  2020/12/3
 */
public class FindNthFromEnd {

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 9, 1, 3, 5, 6, 7};
        ListNode node = ListNode.list2ListNode(arr);
        System.out.println(findNthFromEnd(node, 1));
    }

    /**
     * 一次遍历获取链表的倒数第 k个结点，时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表
     * @param k    倒数第 k 个结点
     * @return 倒数第N个节点的值，N大于链表的长度返回null
     */
    public static Integer findNthFromEnd(ListNode head, int k) {
        // 双指针相隔N，进行遍历
        ListNode pre = head, after = head;
        int index = 1;
        while (after.next != null) {
            after = after.next;
            index++;
            if (index > k) { // 节点当前的位置大于间隔值K时前指针也开始遍历
                pre = pre.next;
            }
        }

        return index < k ? null : pre.val;
    }
}
