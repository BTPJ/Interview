package list;

import list.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表：链表与其反转链表相同
 * <a href="https://leetcode-cn.com/problems/palindrome-linked-list/"/>
 *
 * @author BTPJ 2021/6/10
 */
public class IsPalindrome {

    public static void main(String[] args) {
        int[] arr = { 4, 5, 1, 9, 1, 3 };
        ListNode node = ListNode.list2ListNode(arr);
        System.out.println(isPalindrome(node) ? "是回文链表" : "不是回文链表");
    }

    /**
     * 判断是否是回文链表 时间复杂度O(n),空间复杂度O(n)
     *
     * @param head 链表
     * @return 是否是回文链表
     */
    private static boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();

        // 将链表的值复制到集合中
        ListNode currentNode = head;
        while (currentNode != null) {
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }

        int length = list.size();
        // 只需要遍历链表的前一半元素
        for (int i = 0; i < length / 2; i++) {
            // 如果前一半元素和后一半对应位置的元素不相等，说明链表不是回文链表
            if (list.get(i) != list.get(length - 1 - i)) return false;
        }
        return true;
    }
}
