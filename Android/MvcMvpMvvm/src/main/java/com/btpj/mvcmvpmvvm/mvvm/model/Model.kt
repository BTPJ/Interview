package com.btpj.mvcmvpmvvm.mvvm.model

import com.btpj.mvcmvpmvvm.mvvm.model.bean.LoginResult

/**
 * Model数据层、包含网络请求和一些数据处理等
 *
 * @author LTP 2021/6/21
 */
class Model {

    /**
     * 模拟登录请求
     *
     * @param name 用户名
     * @param password 用户密码
     *
     * @return 登录请求结果LoginResult
     */
    fun login(name: String, password: String): LoginResult {
        if (name != "BTPJ") return LoginResult(false, "用户不存在")

        if (password != "123456") return LoginResult(false, "用户密码有误，请重新输入")

        return LoginResult(true, "用户${name}登录成功")
    }
}