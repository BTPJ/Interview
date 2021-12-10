package com.btpj.design_pattern.factory.factory

import factory.factory.IPush

/**
 * @author LTP  2021/11/10
 */
object PushFactory : AbsPushFactory() {

    override fun <T : IPush> createPush(clazz: Class<T>): T {
        return Class.forName(clazz.name).getDeclaredConstructor().newInstance() as T
    }
}
