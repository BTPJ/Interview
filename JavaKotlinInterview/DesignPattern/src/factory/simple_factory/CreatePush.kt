package com.btpj.design_pattern.factory.simple_factory

/**
 * 具体调用
 *
 * @author LTP  2021/11/10
 */
class CreatePush {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PushFactory.createPush("xiaoMi").push()
            PushFactory.createPush("huaWei").push()
        }
    }
}