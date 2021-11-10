package com.btpj.design_pattern.factory.factory

/**
 * 推送抽象工厂类
 * @author LTP  2021/11/10
 */
abstract class AbsPushFactory {

    abstract fun <T : IPush> createPush(clazz: Class<T>): T
}
