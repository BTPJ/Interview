package collection.set;

import java.util.HashSet;

/**
 * HashSet学习
 * 基于hashMap实现，只存储key,value均为new Object()
 * 基于HashMap,故延续了HashMap的默认初始容量，扩容倍数
 * 无序，不可重复，可为null（拥有hashMap的key的特性）
 *
 * @author LTP  2021/12/10
 */
public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add(null);
        set.add("1");
        set.add("3");
        set.add("2");
        set.add("1");
        System.out.println(set);
    }
}
