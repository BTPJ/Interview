package list;

import com.btpj.leetcode.list.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表：链表与其反转链表相同
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 * @author BTPJ  2021/6/10
 */
public class IsPalindrome {

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 9, 1, 3};
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

        // 使用双指针判断是否回文
        int front = 0;
        int back = list.size() - 1;
        while (front < back) {
            if (!list.get(front).equals(list.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}
