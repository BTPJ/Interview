package com.btpj.mvcmvpmvvm.mvc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Button
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.mvcmvpmvvm.R
import com.btpj.mvcmvpmvvm.databinding.ActivityMvcBinding
import com.btpj.mvcmvpmvvm.mvc.model.Model

/**
 * MVC模式
 * 优点：一定程度上实现了view和model层的分离，降低了耦合性
 * 缺点：Activity/Fragment既充当了View层也充当了Controller层，view与Controller耦合性依旧很高，
 *      后期随着项目复杂度提升，Activity/Fragment也变得越来越臃肿，项目难以维护
 *
 * @author LTP 2021/6/21
 */
//@Route(path = "/MvcMvpMvvm/mvc")
class MvcActivity : BaseBindingActivity<ActivityMvcBinding>(R.layout.activity_mvc) {

    private val model: Model by lazy { Model() }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MvcActivity::class.java)
        }
    }

    override fun setupViews() {
        findViewById<Button>(R.id.btn_login).setOnClickListener { login() }
    }

    /** 登录 */
    @SuppressLint("SetTextI18n")
    private fun login() {
        val result = model.login(
            mBinding.etName.text.toString().trim(),
            mBinding.etPassword.text.toString().trim()
        )
        mBinding.tvResult.apply {
            text = if (result.success) "登录成功" else "登录失败：${result.msg}"
        }
    }
}