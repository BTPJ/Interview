package com.btpj.mvcmvpmvvm.mvc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.btpj.lib_base.base.BaseActivity
//import com.alibaba.android.arouter.facade.annotation.Route
import com.btpj.mvcmvpmvvm.R
import com.btpj.mvcmvpmvvm.StructureActivity
import com.btpj.mvcmvpmvvm.mvc.model.Model
import kotlinx.android.synthetic.main.activity_mvc.*

/**
 * MVC模式
 * 优点：一定程度上实现了view和model层的分离，降低了耦合性
 * 缺点：Activity/Fragment既充当了View层也充当了Controller层，view与Controller耦合性依旧很高，
 *      后期随着项目复杂度提升，Activity/Fragment也变得越来越臃肿，项目难以维护
 *
 * @author LTP 2021/6/21
 */
//@Route(path = "/MvcMvpMvvm/mvc")
class MvcActivity : BaseActivity(R.layout.activity_mvc) {

    private val model: Model by lazy { Model() }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MvcActivity::class.java)
        }
    }

    override fun setupViews() {
        btn_login.setOnClickListener { login() }
    }

    /** 登录 */
    @SuppressLint("SetTextI18n")
    private fun login() {
        val result = model.login(et_name.text.toString().trim(), et_password.text.toString().trim())
        if (result.success) {
            tv_result.text = "登录成功"
        } else {
            tv_result.text = "登录失败：${result.msg}"
        }
    }
}