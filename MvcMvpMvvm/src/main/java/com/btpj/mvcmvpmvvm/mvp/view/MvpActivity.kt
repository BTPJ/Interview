package com.btpj.mvcmvpmvvm.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.btpj.lib_base.base.BaseActivity
import com.btpj.mvcmvpmvvm.R
import com.btpj.mvcmvpmvvm.mvc.MvcActivity
import com.btpj.mvcmvpmvvm.mvp.presenter.MvpPresenter
import kotlinx.android.synthetic.main.activity_mvc.*

/**
 * mvp模式
 * 将MVC中的Controller改成Presenter，Presenter通过接口的方式去调用View层进行视图更新，
 * 解决了MVC中Activity/Fragment既充当Controller又充当了View耦合度高的问题
 *
 * 优点：将业务逻辑写进了Presenter层中，解决了MVC中Controller与View过度耦合的缺点，使得职责更清晰，进一步降低了耦合性，更易维护
 * 缺点：Presenter中不仅包含了业务逻辑，还包含大量的view与model间的手动同步逻辑，随着项目复杂度的提升越来越臃肿
 *
 * @author LTP 2021/6/22
 */
class MvpActivity : BaseActivity(R.layout.activity_mvc), IView {

    private val mMvpPresenter: MvpPresenter by lazy { MvpPresenter(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MvpActivity::class.java)
        }
    }

    override fun setupViews() {
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