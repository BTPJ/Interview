package com.btpj.design_pattern.factory.abstract_factory

/**
 * 推送发短信抽象工厂类
 * @author LTP  2021/11/10
 */
abstract class AbsPushSendFactory {

    abstract fun createPush(): IPush
    abstract fun createSend(): ISend
}
