package com.btpj.design_pattern.proxy.statics;

/**
 * 真实对象类，委托类
 *
 * @author LTP  2021/11/9
 */
public class RealSubject implements Subject {
    @Override
    public void buyHuaWei() {
        System.out.println("我是美国佬，我要买华为");
    }
}
