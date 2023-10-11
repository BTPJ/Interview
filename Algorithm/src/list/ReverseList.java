package list;

import list.base.ListNode;

/**
 * 反转链表
 * <a href="https://leetcode-cn.com/problems/reverse-linked-list/"/>
 *
 * @author BTPJ  2021/6/10
 */
public class ReverseList {

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 9, 1, 3};
        ListNode node = ListNode.list2ListNode(arr);
        System.out.println(ListNode.listNode2String(reverseList2(node)));
    }

    /**
     * 双指针迭代法将链表反转 [时间复杂度：O(n) 空间复杂度：O(1)]
     *
     * @param head 链表（头节点）
     * @return 反转后的新链表
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        // 当前节点
        ListNode cur = head;
        while (cur != null) {
            // 暂存当前节点的下一个节点
            ListNode temp = cur.next;
            // 然后将当前节点指向pre
            cur.next = pre;
            // pre和cur节点都前进一位
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 递归法将链表反转 [时间复杂度：O(n) 空间复杂度：O(n)]
     *
     * @param head 链表
     * @return 反转后的新链表
     */
    public static ListNode reverseList2(ListNode head) {
        // 递归终止条件是当前为空，或者下一个节点为空
        if (head == null || head.next == null) {
            return head;
        } else {
            // 这里的cur就是最后一个节点
            ListNode cur = reverseList2(head.next);
            // 如果链表是 1->2->3->4->5，那么此时的cur就是5
            // 而head是4，head的下一个是5，下下一个是空
            // 所以head.next.next 就是5->4
            head.next.next = head;
            // 防止链表循环，需要将head.next设置为空
            head.next = null;
            return cur;
        }
    }
}
