package com.btpj.mvcmvpmvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.btpj.lib_base.base.BaseActivity
import com.btpj.mvcmvpmvvm.mvc.MvcActivity
import com.btpj.mvcmvpmvvm.mvp.view.MvpActivity
import com.btpj.mvcmvpmvvm.mvvm.MvvmActivity
import kotlinx.android.synthetic.main.activity_structure.*

/**
 * MVC、MVP、MVVM架构
 *
 * @author LTP 2021/8/12
 */
class StructureActivity : BaseActivity(R.layout.activity_structure) {

    companion object {
        // 判断是否是作为Application的首页
        private var isIndexPage = true

        fun newIntent(context: Context): Intent {
            isIndexPage = false
            return Intent(context, StructureActivity::class.java)
        }
    }

    override fun setupViews() {
        titleLayout.setBackVisible(!isIndexPage)

        btn_mvc.setOnClickListener { startActivity(MvcActivity.newIntent(this)) }
        btn_mvp.setOnClickListener { startActivity(MvpActivity.newIntent(this)) }
        btn_mvvm.setOnClickListener { startActivity(MvvmActivity.newIntent(this)) }
    }
}