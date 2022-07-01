package map;

import java.util.Hashtable;

/**
 * 阅读HashTable源码
 * 采用数组+单向链表的数据结构(Node<k,v>[]),同步
 *
 * @author BTPJ  2021/7/20
 */
public class HashTableDemo {

    public static void main(String[] args) {
        Hashtable<String,String> hashtable =new Hashtable<>();
//        hashtable.put(null,"123"); // 会报错，键值均不可为null
        hashtable.put("LTP","123");
        hashtable.put("LTP1","123");
        System.out.println(hashtable.get("LTP"));
        System.out.println(hashtable.get("LTP1"));
    }
}
