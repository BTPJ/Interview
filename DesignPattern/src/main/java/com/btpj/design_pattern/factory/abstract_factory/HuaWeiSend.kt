package com.btpj.design_pattern.factory.abstract_factory

/**
 * 具体产品类：华为短信具体实现
 * @author LTP  2021/11/10
 */
class HuaWeiSend : ISend {

    override fun send() {
        println("华为手机发送华为短信")
    }
}