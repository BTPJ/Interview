package com.btpj.design_pattern.factory.abstract_factory

/**
 * 华为工厂类
 *
 * @author LTP  2021/11/10
 */
class HuaWeiFactory : AbsPushSendFactory() {

    override fun createPush(): IPush {
        return HuaWeiPush()
    }

    override fun createSend(): ISend {
        return HuaWeiSend()
    }
}