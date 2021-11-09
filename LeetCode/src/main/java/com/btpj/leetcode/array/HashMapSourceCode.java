package com.btpj.leetcode.array;

import java.util.HashMap;

/**
 * 阅读HashMap源码
 * 采用数组+单向链表的数据结构(Node<k,v>[])，当链表长度大于8时为了提高搜索效率将链表转换为红黑树；
 * 初始容量为16，负载因子为0.75，即当存储数据超过容量的0.75倍为了降低hash冲突减少链表长度就会触发扩容，
 * 每次扩容都会是之前容量的2倍；扩容时会对数组中的元素重新计算存放位置，并重新插入
 *
 * @author BTPJ  2021/7/20
 */
public class HashMapSourceCode {
    private String a;

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put("LTP", "BTPJ");
        System.out.println(map.size());
        System.out.println(map.get("LTP"));
    }

}
