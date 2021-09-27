package com.btpj.leetcode.list;

import com.btpj.leetcode.list.base.ListNode;

/**
 * 移除链表元素
 * <p>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 1、hashMap的put和get流程
 * 2、hashMap的resize过程
 * 3、hashMap为啥扩容是2^n
 * 4、hashMap1.7与1.8的区别
 * 5、hashMap为啥使用尾插法
 *
 * @author BTPJ  2021/6/9
 */
public class RemoveElements {

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 9, 1, 3};
        ListNode node = ListNode.list2ListNode(arr);
        System.out.println(ListNode.listNode2String(node));
        System.out.println(ListNode.listNode2String(removeElements1(node, 1)));
        System.out.println(ListNode.listNode2String(removeElements2(node, 1)));
    }

    /**
     * 删除链表中的节点，无论是否包含重复节点
     * 采用双指针的方式，时间复杂度：O(n) 空间复杂度：O(n)
     *
     * @param head 链表
     * @param val  指定值
     * @return 删除指定值后的新链表
     */
    public static ListNode removeElements1(ListNode head, int val) {
        if (head == null) return null;

        head.next = removeElements1(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 删除链表中的节点，不含重复节点
     * 采用双指针的方式
     *
     * @param head 链表
     * @param val  指定值
     * @return 删除指定值后的新链表
     */
    public static ListNode removeElements2(ListNode head, int val) {
        if (head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) pre.next = cur.next;
        return head;
    }
}
