package com.btpj.eventbusdemo.my_eventbus;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.btpj.lib_base.utils.LogUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自己实现一个简单的EventBus,更容易理解源码的本质
 *
 * @author LTP  2021/10/22
 */
public class MyEventBus {

    /**
     * 单例
     */
    private static volatile MyEventBus instance;

    /**
     * 存储注册的类以及类中使用了@subscribe注解的方法列表的Map集
     */
    private final Map<Object, List<SubscribeMethod>> mSubscriberMap;

    /**
     * 存储粘性事件列表
     */
    private final Map<Class<?>, Object> mStickyEventMap;

    /**
     * 这里设计成构造私有 (注意：EventBus是public的，表示一条事件总线)
     */
    private MyEventBus() {
        mSubscriberMap = new HashMap<>();
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    /**
     * 使用双重校验锁的方式获取单例
     */
    public static MyEventBus getDefault() {
        if (instance == null) {
            synchronized (MyEventBus.class) {
                if (instance == null) {
                    instance = new MyEventBus();
                }
            }
        }
        return instance;
    }

    /**
     * 注册
     *
     * @param subscriber 订阅者，参数为Object类型，EventBus既可以用到Android，也能用在java中
     */
    public void register(Object subscriber) {
        List<SubscribeMethod> list = mSubscriberMap.get(subscriber);
        if (list == null) {
            list = findSubscribeMethods(subscriber);
            mSubscriberMap.put(subscriber, list);
        }

        for (SubscribeMethod subscribeMethod : list) {
            // 判断是否注册有粘性事件
            if (subscribeMethod.isSticky()) {
                // 遍历mStickyEventMap进行调用
                for (Class clazz : mStickyEventMap.keySet()) {
                    Object event = mStickyEventMap.get(clazz);
                    // 方法的参数Event是否与发送的Event是同一个类
                    if (subscribeMethod.getType().isAssignableFrom(clazz)) {
                        postToSubscription(event, subscriber, subscribeMethod);
                    }
                }
            }
        }
    }

    /**
     * 寻找注册了EventBus类中使用了@subscribe注解的方法
     *
     * @param subscriber 注册了EventBus的注册者
     * @return 注册者中使用了@subscribe注解的方法集合
     */
    private List<SubscribeMethod> findSubscribeMethods(Object subscriber) {
        List<SubscribeMethod> list = new ArrayList<>();
        Class<?> clazz = subscriber.getClass();
        while (clazz != null) {
            // 剔除掉系统的父类，防止无用功，提高性能
            if (clazz.getName().startsWith("java.") || clazz.getName().startsWith("javax.")
                    || clazz.getName().startsWith("android.")) {
                break;
            }

            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                // 找到其中带有@suscribe注解的方法
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe == null) {
                    continue;
                }

                // 判断带有@suscribe注解的方法中的参数是否唯一
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length > 1) {
                    throw new RuntimeException("Event bus only accept one param");
                }
                ThreadMode threadMode = subscribe.threadMode();
                boolean isSticky = subscribe.isSticky();
                SubscribeMethod subscribeMethod = new SubscribeMethod(method, threadMode, parameterTypes[0], isSticky);
                list.add(subscribeMethod);
            }

            clazz = clazz.getSuperclass();
        }
        return list;
    }

    /**
     * 发送普通事件
     *
     * @param event 事件
     */
    public void post(Object event) {
        // 遍历hashMap寻找对应的方法进行调用
        for (Object obj : mSubscriberMap.keySet()) {
            List<SubscribeMethod> list = mSubscriberMap.get(obj);
            assert list != null;
            for (SubscribeMethod subscribeMethod : list) {
                // 方法的参数类是否与设置的类相同
                if (subscribeMethod.getType().isAssignableFrom(event.getClass())) {
                    postToSubscription(event, obj, subscribeMethod);
                }
            }
        }
    }

    /**
     * 根据线程处理事件的响应
     *
     * @param event           事件
     * @param obj             注册者
     * @param subscribeMethod 注册着中的回调方法
     */
    private void postToSubscription(Object event, Object obj, SubscribeMethod subscribeMethod) {
        switch (subscribeMethod.getThreadMode()) {
            case MAIN:
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    // 主 - 主
                    invoke(subscribeMethod, obj, event);
                } else {
                    // 子 - 主
                    LogUtil.INSTANCE.d("发送的线程名：" + Thread.currentThread().getName());
                    new Handler(Looper.getMainLooper()).post(() -> invoke(subscribeMethod, obj, event));
                }
                break;
            case BACKGROUND:
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    // 主 - 子
                    // EventBus是利用ExecutorService实现的
                    HandlerThread my_thread = new HandlerThread("My Thread") {
                        @Override
                        protected void onLooperPrepared() {
                            super.onLooperPrepared();
                            new Handler().post(() -> {
                                LogUtil.INSTANCE.d("发送的线程名：" + Thread.currentThread().getName());
                                invoke(subscribeMethod, obj, event);
                            });
                        }
                    };
                    my_thread.start();
                } else {
                    // 子 - 子
                    LogUtil.INSTANCE.d("发送的线程名：" + Thread.currentThread().getName());
                    invoke(subscribeMethod, obj, event);
                }
                break;
        }
    }

    /**
     * 利用反射调用回调方法
     *
     * @param subscribeMethod SubscribeMethod
     * @param obj             方法所在的类
     * @param event           方法参数
     */
    private void invoke(SubscribeMethod subscribeMethod, Object obj, Object event) {
        Method method = subscribeMethod.getMethod();
        try {
            method.invoke(obj, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送粘性事件
     *
     * @param event 粘性事件
     */
    public void postSticky(Object event) {
        mStickyEventMap.put(event.getClass(), event);
    }

    public void unregister(Object subscriber) {
        mSubscriberMap.clear();
        mStickyEventMap.clear();
    }
}
