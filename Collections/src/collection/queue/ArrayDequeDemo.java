package collection.queue;

import java.util.ArrayDeque;

/**
 * ArrayDeque学习
 *
 * @author BTPJ  2021/12/10
 */
public class ArrayDequeDemo {

    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        // arrayDeque.add(null);  // 不可存储null
        arrayDeque.add("2");
        arrayDeque.add("1");
        arrayDeque.add("2");
        arrayDeque.add("3");
        System.out.println(arrayDeque);
    }
}
