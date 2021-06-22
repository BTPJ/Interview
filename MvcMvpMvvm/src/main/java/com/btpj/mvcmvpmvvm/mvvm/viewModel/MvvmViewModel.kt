package com.btpj.mvcmvpmvvm.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.btpj.mvcmvpmvvm.mvvm.model.Model
import com.btpj.mvcmvpmvvm.mvvm.model.bean.LoginResult

/**
 * viewModel层
 *
 * @author LTP 2021/6/22
 */
class MvvmViewModel : ViewModel() {

    val userName = MutableLiveData("")
    val password = MutableLiveData("")
    val loginResult = MutableLiveData<LoginResult>()

    private val mModel = Model()

    /** 登录逻辑 */
    fun login() {
        loginResult.value = mModel.login(userName.value!!, password.value!!)
    }
}