package com.btpj.activity.launch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.btpj.activity.R
import com.btpj.activity.databinding.ActivityABinding
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.lib_base.utils.LogUtil

/**
 * ActivityA跳转到ActivityB的生命周期
 * A 启动：A onCreate -> A onStart -> A onResume
 * A - B：A onPause -> B onCreate -> B onStart -> B onResume -> A onStop
 * B返回：B onPause -> A onRestart -> A onStart -> A onResume -> B onStop -> B onDestroy
 *
 * @author LTP 2021/10/12
 */
class AActivity : BaseBindingActivity<ActivityABinding>(R.layout.activity_a) {

    companion object {
        /**
         * 启动Activity
         *
         * @param context Context
         */
        fun launch(context: Context) {
            context.startActivity(Intent(context, AActivity::class.java))
        }
    }

    override fun setupViews() {
        mBinding.btnLaunch2ActivityB.setOnClickListener { BActivity.launch(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d("A onCreate")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d("A onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.d("A onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d("A onPause")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d("A onStop")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtil.d("A onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d("A onDestroy")
    }
}