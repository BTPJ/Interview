package com.btpj.mvcmvpmvvm.mvp.view

/**
 * Presenter与View层交互的接口，提供View的一些接口方法
 *
 * @author LTP 2021/6/22
 */
interface IView {

    fun showSuccessView()

    fun showFailedView(errorMsg: String)
}