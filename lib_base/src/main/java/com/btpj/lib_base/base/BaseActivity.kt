package com.btpj.lib_base.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.btpj.lib_base.utils.StatusBarUtil

/**
 * 普通的Activity基类
 *
 * @author LTP 2021/6/23
 */
abstract class BaseActivity(private val contentViewResId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResId)
        StatusBarUtil.setImmersionStatus(this)
        setupViews()
    }

    /** Views相关初始化设置 */
    abstract fun setupViews()
}