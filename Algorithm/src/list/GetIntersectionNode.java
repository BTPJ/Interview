package list;

import list.base.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 相较链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * 链接：<a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/">...</a>
 *
 * @author BTPJ  2023/4/13
 */
public class GetIntersectionNode {

    public static void main(String[] args) {

    }

    /**
     * 哈希表发
     *
     * @param headA A节点
     * @param headB B节点
     * @return 相交节点
     */
    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();

        ListNode temp = headA;
        while (temp != null) {
            set.add(temp);
            temp = temp.next;
        }

        temp = headB;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    /**
     * 哈希表发
     *
     * @param headA A节点
     * @param headB B节点
     * @return 相交节点
     */
    private static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointA = headA;
        ListNode pointB = headB;
        while (pointA != pointB) {
            pointA = pointA == null ? headB : pointA.next;
            pointB = pointB == null ? headA : pointB.next;
        }
        return pointA;
    }
}
