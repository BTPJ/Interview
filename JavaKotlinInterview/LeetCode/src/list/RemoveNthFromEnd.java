package list;

import list.base.ListNode;

/**
 * 删除链表的倒数第 N 个结点
 * <p>
 * 给定一个链表，删除链表的倒数第  n  个节点，并且返回链表的头结点
 * 示例：给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 *
 * @author BTPJ  2020/12/3
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 9, 1, 3};
        ListNode node = ListNode.list2ListNode(arr);
        System.out.println(ListNode.listNode2String(removeNthFromEnd(node, 2)));
    }

    /**
     * 删除链表的倒数第 N 个结点，时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表
     * @param n    倒数第 N 个结点
     * @return 新链表
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(dummy);
        ListNode cur = dummy;
        for (int i = 0; i < length - n - 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    /**
     * 获取链表的长度
     *
     * @param node 链表
     * @return 链表的长度
     */
    private static int getLength(ListNode node) {
        int length = 0;
        while (node != null) {
            ++length;
            node = node.next;
        }
        return length;
    }
}
