package com.btpj.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Java arrayList遍历删除指定元素
 * tips:使用for循环会导致ConcurrentModificationException
 * 参考：https://www.cnblogs.com/kintanx/p/10708491.html
 *
 * @author BTPJ  2021/6/17
 */
public class RemoveArraylistItem {

    static List<Integer> list = new ArrayList<>(Arrays.asList(0, 11, 22,22, 33, 44, 55));

    public static void main(String[] args) {
        System.out.println(getList3());
    }

    /**
     * 使用foreach迭代会抛ConcurrentModificationException异常，主要有modCount 和 expectedModCount不一致
     * @return list
     */
    private static List<Integer> getList1() {
        for (int i : list) {
            if (i == 22) {
                list.remove(i);
            }
        }
        return list;
    }

    /**
     *  使用普通for循环不会导致IndexOutOfBoundsException
     * @return list
     */
    private static List<Integer> getList2() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 22) {
                list.remove(i);
                // 这里需要加上i--,遗漏了被删除元素后的一个元素，譬如连续两个22，第二个会跳过带来错误结果，并不是会抛
                i--;
            }
        }
        return list;
    }

    /**
     *  使用Iterator迭代器也可以
     * @return list
     */
    private static List<Integer> getList3() {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next()==22){
                iterator.remove();
            }
        }
        return list;
    }

    /**
     *  java8 的removeIf（本质上也是Iterator）
     * @return list
     */
    private static List<Integer> getList4() {
        list.removeIf(item -> item == 22);
        return list;
    }
}
