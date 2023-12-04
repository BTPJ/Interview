package list;

import list.base.ListNode;

import java.util.ArrayDeque;

/**
 * 删除链表的倒数第 k 个结点
 * <p>
 * 给定一个链表，删除链表的倒数第 k 个节点，并且返回链表的头结点
 * 示例：给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5
 * 链接：<a href=
 * "https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list"/>
 *
 * @author BTPJ 2020/12/3
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        int[] arr = { 1, 2 };
        ListNode head = ListNode.list2ListNode(arr);
        System.out.println(ListNode.listNode2String(head));
        // System.out.println(ListNode.listNode2String(removeNthFromEnd(head, 2)));
        System.out.println(ListNode.listNode2String(removeNthFromEnd2(head, 2)));
        // System.out.println(ListNode.listNode2String(removeNthFromEnd3(head, 2)));
    }

    /**
     * 迭代法，需要两次遍历
     * 删除链表的倒数第K个结点，时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表
     * @param n    倒数第n个结点
     * @return 新链表
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        // 创建一个新节点对象，使不受head移位的影响
        // 这个0不重要，主要是为了添加一个next为head的新节点
        ListNode newListNode = new ListNode(0, head);
        // 不断移动的节点
        ListNode cur = head;
        for (int i = 0; i < getLength(head) - n - 1; i++) {
            cur = cur.next;
        }
        // 到达倒数第n-1个节点，让该节点的next跳过下一个节点指向下下个节点
        cur.next = cur.next.next;
        return newListNode.next;
    }

    /**
     * 获取链表的长度
     *
     * @param head 链表
     * @return 链表的长度
     */
    private static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            // node不为空时，需+1再返回
            ++length;
        }
        return length;
    }

    /**
     * 前后指针一次遍历获取链表的倒数第 n个结点，时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表
     * @param n    倒数第 N 个结点
     * @return 新链表
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 临时链表,头节点指向head
        ListNode temp = new ListNode(0, head);
        // 双指针相隔N，进行遍历
        ListNode pre = temp, after = temp;
        int index = 0;
        while (pre.next != null) {
            pre = pre.next;
            index++;
            if (index > n) {
                // 节点当前的位置大于间隔值K时前指针也开始遍历
                after = after.next;
            }
        }
        // 将后节点的下一个节点指向下下个节点
        after.next = after.next.next;
        return temp.next;
    }

    /**
     * 借助栈来获取链表的倒数第 n个结点，时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head 链表
     * @param n    倒数第 N 个结点
     * @return 新链表
     */
    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode newListNode = new ListNode(0, head);
        // 实现栈有三种类，包括Stack、ArrayDeque以及Stack，ArrayDeque相对效率更优
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        // 现在栈最上方的节点刚好就是倒数第N+1个节点
        ListNode temp = stack.peek();
        // 将倒数第N+1个节点的下一个节点指向倒数第N-1个节点即达到了删除倒数第N个几点的目的
        temp.next = temp.next.next;
        return newListNode.next;
    }
}
