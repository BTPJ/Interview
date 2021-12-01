package com.btpj.mvcmvpmvvm.mvvm

import android.content.Context
import android.content.Intent
import com.btpj.lib_base.base.BaseVMBActivity
import com.btpj.mvcmvpmvvm.R
import com.btpj.mvcmvpmvvm.databinding.ActivityMvvmBinding
import com.btpj.mvcmvpmvvm.mvvm.viewModel.MvvmViewModel
import java.util.*

/**
 * MVVM模式
 * view与model间的同步逻辑自动化了
 * 优点：View和Model使用DataBinding来进行双向绑定，解决了Presenter中包含大量的手动同步model和view的逻辑，使项目耦合度更低，降低维护成本
 * 缺点：视图绑定的申明写在view的模版当中，无法断点调试
 *
 * @author LTP 2021/6/22
 */
class MvvmActivity : BaseVMBActivity<MvvmViewModel, ActivityMvvmBinding>(R.layout.activity_mvvm) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MvvmActivity::class.java)
        }
    }

    override fun setupVMBViews() {}
}