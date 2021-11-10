package com.btpj.design_pattern.factory.abstract_factory

/**
 * 具体产品类：小米短信具体实现
 * @author LTP  2021/11/10
 */
class MiSend : ISend {

    override fun send() {
        println("小米手机发送小米短信")
    }
}