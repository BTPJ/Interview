package com.btpj.design_pattern.observer

/**
 * 具体观察者
 * @author LTP  2021/11/10
 */
class ConcreteObserver(private val name: String) : Observer {

    override fun update(msg: String) {
        println("${name}被通知：$msg")
    }
}