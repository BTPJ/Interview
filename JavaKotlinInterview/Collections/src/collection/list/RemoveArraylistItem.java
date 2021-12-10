package collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Java arrayList遍历删除指定元素
 * tips:
 * 1、使用增强for循环会导致ConcurrentModificationException异常
 * 2、使用普通for循环正序遍历会导致有重复元素时，后面的元素无法被遍历，假如后面有相同的元素就会导致集合中仍有该元素，解决方法是找到一个进行i--操作
 * 3、进行普通for循环的倒叙遍历可解
 * 4、使用foreach遍历会导致java.util.ConcurrentModificationException异常
 * 5、使用Iterator迭代器也可以，但需要使用iterator.remove方法而不是list.remove
 * 6、使用java8的removeIf（本质上也是Iterator）
 * <p>
 * 参考：https://www.cnblogs.com/kintanx/p/10708491.html
 *
 * @author BTPJ  2021/6/17
 */
public class RemoveArraylistItem {

    private static List<String> list = new ArrayList<>(Arrays.asList("0", "11", "22", "23", "33", "22", "55"));

    public static void main(String[] args) {
//        System.out.println(getList1());
        System.out.println(getList2());
//        System.out.println(getList3());
//        System.out.println(getList4());
//        System.out.println(getList5());
//        System.out.println(getList6());
    }

    /**
     * 使用增强for循环会抛ConcurrentModificationException异常
     *
     * @return list
     */
    private static List<String> getList1() {
        for (String i : list) {
            if ("22".equals(i)) {
                list.remove(i);
            }
        }
        return list;
    }

    /**
     * 使用普通for循环正序遍历会导致有重复元素时，后面的元素无法被遍历，假如后面有相同的元素就会导致集合中仍有该元素，
     * 解决方法是找到一个进行i--操作
     *
     * @return list
     */
    private static List<String> getList2() {
        for (int i = 0; i < list.size(); i++) {
            if ("22".equals(list.get(i))) {
                list.remove(i);
                // 这里需要加上i--,遗漏了被删除元素后的一个元素，譬如连续两个22，第二个会跳过带来错误结果，并不是会抛异常
                i--;
            }
        }
        return list;
    }

    /**
     * 使用普通for循环倒序遍历可解
     *
     * @return list
     */
    private static List<String> getList3() {
        for (int i = list.size() - 1; i >= 0; i--) {
            if ("22".equals(list.get(i))) {
                list.remove(i);
            }
        }
        return list;
    }


    /**
     * 使用forEach循环会导致 java.util.ConcurrentModificationException异常
     * 本质上跟
     *
     * @return list
     */
    private static List<String> getList4() {
        // forEach本质上就是迭代器
        list.forEach(i -> {
            if ("22".equals(i)) {
                list.remove(i);
            }
        });
        return list;
    }

    /**
     * 使用Iterator迭代器也可以，但需要使用iterator.remove方法而不是list.remove
     *
     * @return list
     */
    private static List<String> getList5() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if ("22".equals(iterator.next())) {
                iterator.remove(); // 正确方式
//                list.remove(iterator.next());  // 会导致ConcurrentModificationException异常
            }
        }
        return list;
    }

    /**
     * java8的removeIf（本质上也是Iterator）
     *
     * @return list
     */
    private static List<String> getList6() {
        list.removeIf("22"::equals);
        return list;
    }
}
