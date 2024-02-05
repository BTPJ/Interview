package list;

import java.util.HashSet;
import java.util.Set;

import list.base.ListNode;

/**
 * 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 链接：<a href="https://leetcode.cn/problems/linked-list-cycle/">...</a>
 *
 * @author BTPJ 2023/3/18
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
        System.out.println(hasCycle2(head) ? "是环形链表" : "不是环形链表");
    }

    /**
     * 哈希法，时间复杂度O(n) 空间复杂度O(n)
     *
     * @param head 链表
     * @return 是否有环
     */
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

    /**
     * 双指针/快慢指针法，时间复杂度O(n) 空间复杂度O(1)
     *
     * @param head 链表
     * @return 是否有环
     */
    private static boolean hasCycle2(ListNode head) {
        // 如果链表为空或只有一个节点，不存在环，返回 false
        if (head == null || head.next == null)
            return false;

        // 慢指针从头节点开始移动
        ListNode slow = head;
        // 快指针从头节点的下一个节点开始移动
        ListNode fast = head.next; 
        while (fast != null && fast.next != null) { 
            // 如果快指针和慢指针指向同一个节点，说明链表中存在环，返回 true
            if (fast == slow) return true; 

            fast = fast.next.next; // 快指针向后移动两个节点
            slow = slow.next; // 慢指针向后移动一个节点
        }
        return false; 
    }
}
