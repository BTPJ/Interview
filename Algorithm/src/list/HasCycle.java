package list;

import java.util.HashSet;
import java.util.Set;

import list.base.ListNode;

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
        if (head == null || head.next == null) return false;

        ListNode lowPoint = head;
        ListNode fastPoint = head.next;
        while (fastPoint != null && fastPoint.next != null) {
            if (fastPoint == lowPoint) return true;
            
            fastPoint = fastPoint.next.next;
            lowPoint = lowPoint.next;
        }
        return false;
    }
}
