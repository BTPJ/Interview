package list;

import list.base.ListNode;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 链接：<a href="https://leetcode.cn/problems/merge-two-sorted-lists/?envType=study-plan&id=shu-ju-jie-gou-ru-men&plan=data-structures&plan_progress=xhd088o3">...</a>
 *
 * @author BTPJ  2023/3/20
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 3, 4, 5};
        int[] arr2 = {1, 3, 4};
        ListNode list1 = ListNode.list2ListNode(arr1);
        ListNode list2 = ListNode.list2ListNode(arr2);
        System.out.println(ListNode.listNode2String(mergeTwoLists1(list1, list2)));
        System.out.println(ListNode.listNode2String(mergeTwoLists2(list1, list2)));
    }

    /**
     * 递归法
     *
     * @param list1 链表1
     * @param list2 链表2
     * @return 合并后的链表
     */
    private static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            return new ListNode(list1.val, mergeTwoLists1(list1.next, list2));
        } else {
            return new ListNode(list2.val, mergeTwoLists1(list1, list2.next));
        }
    }

    /**
     * 迭代法
     *
     * @param list1 链表1
     * @param list2 链表2
     * @return 合并后的链表
     */
    private static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode(-1);
        ListNode pointNode = listNode;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pointNode.next = list1;
                list1 = list1.next;
            } else {
                pointNode.next = list2;
                list2 = list2.next;
            }
            pointNode = pointNode.next;
        }

        // 可能存在一个遍历完毕，一个还未遍历完毕
        pointNode.next = list1 == null ? list2 : list1;

        return listNode.next;
    }
}
