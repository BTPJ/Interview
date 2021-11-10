package com.btpj.design_pattern.observer

/**
 * 具体被观察者
 *
 * @author LTP  2021/11/10
 */
class ConcreteSubject : Subject {
    /** 存储绑定的观察者 */
    private val mObserverList = ArrayList<Observer>()

    override fun attach(observer: Observer) {
        mObserverList.add(observer)
    }

    override fun detach(observer: Observer) {
        mObserverList.remove(observer)
    }

    override fun notify(msg: String) {
        // 循环遍历观察者逐个通知
        mObserverList.forEach { observer -> observer.update(msg) }
    }
}