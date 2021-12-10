package collection.set;

import java.util.LinkedHashSet;

/**
 * TreeSet学习
 * 基于TreeMap实现，只存储key,value均为new Object()
 * 基于HashMap,故延续了HashMap的默认初始容量，扩容倍数
 * 无序，不可重复，可为null（拥有hashMap的key的特性）
 *
 * @author LTP  2021/12/10
 */
public class LinkedHashSetDemo {

    public static void main(String[] args) {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        // set.add(null); LinkedHashMap的key值不能为null,故LinkedHashMap也无法存储null
        set.add("1");
        set.add("3");
        set.add("2");
        set.add("4");
        set.add("1");
        System.out.println(set);
    }
}
