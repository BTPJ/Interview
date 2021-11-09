package com.btpj.design_pattern.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * 实际调用动态代理的位置
 * 动态代理中，代理类与委托类的关系是运行时才确定的，
 * 代理对象可同时代理多个任务而不需要修改代理类的源码，只需要添加新的委托类以及委托方法即可，可同时代理多个类型的目标对象
 *
 * @author LTP  2021/11/9
 */
public class Client {
    public static void main(String[] args) {
        // 创建委托类1
        RealSubject realSubject = new RealSubject();
        // 创建代理类，并将委托类1传入构造
        DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, new Class[]{Subject.class}, dynamicProxy);
        subject.buyHuaWei();

        // 创建委托类2
        RealSubject2 realSubject2 = new RealSubject2();
        // 创建代理类，并将委托类2传入构造
        DynamicProxy dynamicProxy2 = new DynamicProxy(realSubject2);
        ClassLoader classLoader2 = realSubject2.getClass().getClassLoader();
        Subject2 subject2 = (Subject2) Proxy.newProxyInstance(classLoader2, new Class[]{Subject2.class}, dynamicProxy2);
        subject2.buyXiaomi();
    }
}
