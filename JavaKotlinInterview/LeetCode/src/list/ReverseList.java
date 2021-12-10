package list;

import com.btpj.leetcode.list.base.ListNode;

/**
 * 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author BTPJ  2021/6/10
 */
public class ReverseList {

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 9, 1, 3};
        ListNode node = ListNode.list2ListNode(arr);
        System.out.println(ListNode.listNode2String(reverseList(node)));
    }

    /**
     * 将链表反转 [时间复杂度：O(n) 空间复杂度：O(1)]
     *
     * @param head 链表
     * @return 反转后的新链表
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            // 记录当前节点的下一个节点
            ListNode next = cur.next;
            // 然后将当前节点指向pre
            cur.next = pre;
            // pre和cur节点都前进一位
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
