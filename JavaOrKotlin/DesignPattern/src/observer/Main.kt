package com.btpj.design_pattern.observer

/**
 * 调用类
 *
 * @author LTP  2021/11/10
 */
class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val subject = ConcreteSubject()

            val observer1 = ConcreteObserver("观察者1")
            val observer2 = ConcreteObserver("观察者2")
            val observer3 = ConcreteObserver("观察者3")

            subject.attach(observer1)
            subject.attach(observer2)
            subject.attach(observer3)

            subject.notify("我要更新啦")

            subject.detach(observer2)
            subject.notify("我删掉观察者2再更新一次")
        }
    }
}