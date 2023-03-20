package list;

import list.base.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 链接：<a href="https://leetcode.cn/problems/linked-list-cycle/">...</a>
 *
 * @author BTPJ  2023/3/18
 */
public class HasCycle {

    public static void main(String[] args) {
        ListNode tail = new ListNode(5);
        ListNode middle3 = new ListNode(4, tail);
        ListNode middle2 = new ListNode(3, middle3);
        // tail.next = middle2;
        ListNode middle1 = new ListNode(2, middle2);
        ListNode head = new ListNode(1, middle1);
        System.out.println(hasCycle(head) ? "是环形链表" : "不是环形链表");
    }

    private static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
