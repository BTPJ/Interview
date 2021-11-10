package com.btpj.design_pattern.factory.factory

/**
 * 具体产品类：Oppo推送具体实现
 * @author LTP  2021/11/10
 */
class OppoPush : IPush {

    override fun push() {
        println("oppo手机使用oppo推送")
    }
}