package list.base;

/**
 * 链表
 *
 * @author BTPJ  2020/12/3
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

//    @Override
//    public String toString() {
//        ListNode node = next;
//        StringBuilder sb = new StringBuilder();
//        sb.append("[").append(val);
//        while (node.next != null) {
//            sb.append(node.val).append(",");
//            node = node.next;
//        }
//        return sb.append("]").toString();
//    }


    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    /**
     * 将数组转化为ListNode
     *
     * @param arr 数组
     * @return ListNode
     */
    public static ListNode list2ListNode(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode root = new ListNode(arr[0]);
        ListNode tempNode = root;
        for (int i = 1; i < arr.length; i++) {
            ListNode temp = new ListNode(arr[i]);
            tempNode.next = temp;
            tempNode = temp;
        }
        return root;
    }

    /**
     * 将ListNode转化为Array的字符串
     *
     * @param root ListNode
     * @return ListNode
     */
    public static String listNode2String(ListNode root) {
        ListNode node = root;
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(root.val).append(",");
        while (node.next != null) {
            sb.append(node.next.val).append(",");
            node = node.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.append("]").toString();
    }
}
