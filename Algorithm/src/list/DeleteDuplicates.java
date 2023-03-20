package list;

import list.base.ListNode;

/**
 * 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 链接：<a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/?envType=study-plan&id=shu-ju-jie-gou-ru-men&plan=data-structures&plan_progress=xhd088o3">...</a>
 *
 * @author BTPJ  2023/3/20
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 4, 5, 9};
        ListNode node = ListNode.list2ListNode(arr);
        System.out.println(ListNode.listNode2String(deleteDuplicates(node)));
    }

    private static ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
