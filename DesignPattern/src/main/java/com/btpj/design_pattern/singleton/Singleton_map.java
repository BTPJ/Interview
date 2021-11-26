package com.btpj.design_pattern.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式 ~ 容器
 * 多种单例类统一管理，在使用时根据key获取对象对应类型的对象。
 * 这种方式使得我们可以管理多种类型的单例，并且在使用时可以通过统一的接口进行获取操作，
 * 降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度
 *
 * @author LTP 2021/11/5
 */
public class Singleton_map {

    /**
     * 使用Map容器类统一管理各单例实例
     */
    private static Map<String, Object> mObjMap = new ConcurrentHashMap<>();

    private Singleton_map() {
    }

    /**
     * 将实例保存至Map容器
     *
     * @param key      key
     * @param instance 实例
     */
    public static void addInstance(String key, Object instance) {
        if (!mObjMap.containsKey(key)) {
            mObjMap.put(key, instance);
        }
    }

    /**
     * 根据key获取单例类
     *
     * @param key key
     * @return 获取到的单例类
     */
    public static Object getInstance(String key) {
        return mObjMap.get(key);
    }
}