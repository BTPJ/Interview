package com.btpj.collection.map;

import java.util.HashMap;

/**
 * 阅读HashMap源码
 * 采用数组+单向链表的数据结构(Node<k,v>[])，当链表长度大于8时为了提高搜索效率将链表转换为红黑树；
 * 初始容量为16，负载因子为0.75，即当存储数据超过容量的0.75倍为了降低hash冲突减少链表长度就会触发扩容，
 * 每次扩容都会是之前容量的2倍；扩容时会对数组中的元素重新计算存放位置，并重新插入
 *
 * @author BTPJ  2021/7/20
 */
public class HashMapDemo {

    public static void main(String[] args) {
        // HashMap采用数组 + 单向链表 + 红黑树（链表长度 > 8自动转为红黑树）的数据结构
        // 默认初始容量为16，当构造器中传入的容量不为2^n时会自动转换为刚好大于传入容量的2^n的数值
        // 负载因子为0.75，即当存储数据超过容量的0.75倍为了降低hash冲突减少链表长度就会触发扩容
        HashMap<String,String> map = new HashMap<>();

        // put流程：实质上时调用putVal方法
        // 1、第一次put元素时会触发resize方法，其实是将hashMap的Node[]数组初始化工作进行了类似懒加载的处理
        // 2、将hash值与capacity-1进行&运算计算出当前key要放置在数组中的位置；
        // 当该位置无值时就会直接初始化创建一个Node(hash,key,value,null)并放置在该位置，
        // 如果已有值就先判断存储和插入的key是否相等，相等的话通过onlyIfAbsent参数判定是否要覆盖更新并返回旧值
        // 3、如果已有值并且与要存储的key不等，就先判定该Node是否是一个TreeNode(红黑树，Node的子类),是的话就调用putTreeVal方法执行红黑树的插入操作
        // 4、如果已有值并且与要存储的key不等也不是一个红黑树节点TreeNode就会对Node链表进行遍历操作，
        // 遍历到链表中有相同key就跳出根据onlyIfAbsent参数判定是否要覆盖更新，如果没有便新建Node，
        // 放置在Node链表的Next位置；如果链表长度超过8时便会将链表转化为红黑树并重新插入
        // 5、最后判断HashMap存储的元素是否超过了阈值，超过阈值便会执行resize扩容操作，并且每次扩容都是之前的2倍。
        // 扩容后重新进行hash&(capacity-1)计算元素的插入位置重新插入
        map.put("LTP", "BTPJ");
//        System.out.println(map.putIfAbsent("LTP", "BTPJ1"));
        System.out.println(map.put("LTP", "BTPJ1"));
        System.out.println(map.put(null,"1"));
        System.out.println(map.size());
        System.out.println(map.get("LTP"));
    }

}
