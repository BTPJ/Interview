package com.btpj.eventbusdemo.my_eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Subscribe注解
 *
 * @author LTP  2021/10/22
 */
@Target(ElementType.METHOD)
// 设置成运行时才可以运行时利用反射获取利用了该注解的方法
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    /**
     * 是否接收粘性事件默认为false
     *
     * @return 是否接收粘性事件
     */
    boolean isSticky() default false;

    /**
     * 线程类型
     *
     * @return 线程类型
     */
    ThreadMode threadMode() default ThreadMode.MAIN;
}
