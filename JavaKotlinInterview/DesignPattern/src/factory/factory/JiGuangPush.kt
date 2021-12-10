package com.btpj.design_pattern.factory.factory

import factory.factory.IPush

/**
 * 具体产品类：极光推送具体实现
 * @author LTP  2021/11/10
 */
class JiGuangPush : IPush {

    override fun push() {
        println("其他手机使用极光推送")
    }
}
