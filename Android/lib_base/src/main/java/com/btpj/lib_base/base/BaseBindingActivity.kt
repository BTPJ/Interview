package com.btpj.lib_base.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.btpj.lib_base.BR
import com.btpj.lib_base.R
import com.btpj.lib_base.utils.StatusBarUtil

/**
 * 集成DataBinding的Activity基类
 *
 * @author LTP 2021/6/23
 */
abstract class BaseBindingActivity<B : ViewDataBinding>(private val contentViewResId: Int) :
    AppCompatActivity() {

    /** DataBinding基类 */
    protected lateinit var mBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResId)
        StatusBarUtil.setImmersionStatus(this)
        setupDataBinding()
        setupViews()
    }

    /** DataBinding相关初始化设置 */
    private fun setupDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, contentViewResId)
        mBinding.lifecycleOwner = this
    }

    /** Views相关初始化设置 */
    abstract fun setupViews()
}