package com.btpj.mvcmvpmvvm

import android.content.Context
import android.content.Intent
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.mvcmvpmvvm.databinding.ActivityStructureBinding
import com.btpj.mvcmvpmvvm.mvc.MvcActivity
import com.btpj.mvcmvpmvvm.mvp.view.MvpActivity
import com.btpj.mvcmvpmvvm.mvvm.MvvmActivity

/**
 * MVC、MVP、MVVM架构
 *
 * @author LTP 2021/8/12
 */
class StructureActivity :
    BaseBindingActivity<ActivityStructureBinding>(R.layout.activity_structure) {

    companion object {
        // 判断是否是作为Application的首页
        private var isIndexPage = true

        fun newIntent(context: Context): Intent {
            isIndexPage = false
            return Intent(context, StructureActivity::class.java)
        }
    }

    override fun setupViews() {
        mBinding.apply {
            titleLayout.setBackVisible(!isIndexPage)
            btnMvc.setOnClickListener { startActivity(MvcActivity.newIntent(this@StructureActivity)) }
            btnMvp.setOnClickListener { startActivity(MvpActivity.newIntent(this@StructureActivity)) }
            btnMvvm.setOnClickListener { startActivity(MvvmActivity.newIntent(this@StructureActivity)) }
        }
    }
}