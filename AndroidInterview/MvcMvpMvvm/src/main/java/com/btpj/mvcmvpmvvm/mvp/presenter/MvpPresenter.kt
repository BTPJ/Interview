package com.btpj.mvcmvpmvvm.mvp.presenter

import com.btpj.mvcmvpmvvm.mvp.model.Model
import com.btpj.mvcmvpmvvm.mvp.view.IView

/**
 * Presenter层，View与Model交互的桥梁
 *
 * @author LTP 2021/6/22
 */
class MvpPresenter(private val iView: IView) {

    private var mModel: Model = Model()

    /**
     * 登录逻辑
     *
     * @param name 用户名
     * @param password 用户密码
     */
    fun login(name: String, password: String) {
        val result = mModel.login(name, password)
        if (result.success) {
            iView.showSuccessView()
        } else {
            iView.showFailedView(result.msg)
        }
    }
}