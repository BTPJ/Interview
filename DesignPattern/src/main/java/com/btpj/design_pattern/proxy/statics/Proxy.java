package com.btpj.design_pattern.proxy.statics;

/**
 * 代理类
 *
 * @author LTP  2021/11/9
 */
public class Proxy implements Subject {
    private final Subject subject;

    /**
     * 构造器传入代理对象
     *
     * @param subject 实现代理方法的代理对象
     */
    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void buyHuaWei() {
        // 收取代购费用
        System.out.println("收取代购费用");
        subject.buyHuaWei();
        // 一些其他的骚操作
        System.out.println("快递代理货物");
    }
}
