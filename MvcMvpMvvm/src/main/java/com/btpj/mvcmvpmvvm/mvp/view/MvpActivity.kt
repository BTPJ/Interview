package com.btpj.mvcmvpmvvm.mvp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.btpj.mvcmvpmvvm.R
import com.btpj.mvcmvpmvvm.mvp.presenter.MvpPresenter
import kotlinx.android.synthetic.main.activity_mvc.*

/**
 * mvp模式
 * 优点：将业务逻辑写进了Presenter层中，解决了MVC中Controller与View过度耦合的缺点，使得指责更清晰，进一步降低了耦合性，更易维护
 * 缺点：接口数量大，Presenter中包含大量的view与model间的手动同步逻辑，随着项目复杂度的提升越来越臃肿
 *
 * @author LTP 2021/6/22
 */
class MvpActivity : AppCompatActivity(), IView {

    private lateinit var mMvpPresenter: MvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        init()
    }

    private fun init() {
        mMvpPresenter = MvpPresenter(this)
        btn_login.setOnClickListener {
            mMvpPresenter.login(
                et_name.text.toString().trim(),
                et_password.text.toString().trim()
            )
        }
    }

    override fun showSuccessView() {
        tv_result.text = "登录成功"
    }

    @SuppressLint("SetTextI18n")
    override fun showFailedView(errorMsg: String) {
        tv_result.text = "登录失败：$errorMsg"
    }
}