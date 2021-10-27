package com.btpj.eventbusdemo.my_eventbus;

import java.lang.reflect.Method;

/**
 * 回调方法实体
 *
 * @author LTP  2021/10/22
 */
public class SubscribeMethod {
    /**
     * 使用@subscribe注解回调的方法
     */
    private Method method;

    /**
     * 线程模式
     */
    private ThreadMode threadMode;

    /**
     * 回调方法中的参数
     */
    private Class<?> type;

    /**
     * 是否是粘性事件的方法回调
     */
    private boolean isSticky;

    public SubscribeMethod(Method method, ThreadMode threadMode, Class<?> type, boolean isSticky) {
        this.method = method;
        this.threadMode = threadMode;
        this.type = type;
        this.isSticky = isSticky;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public boolean isSticky() {
        return isSticky;
    }

    public void setSticky(boolean sticky) {
        isSticky = sticky;
    }
}
