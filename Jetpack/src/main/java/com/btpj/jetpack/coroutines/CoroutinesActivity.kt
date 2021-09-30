package com.btpj.jetpack.coroutines

import android.content.Context
import android.content.Intent
import com.btpj.jetpack.R
import com.btpj.jetpack.databinding.ActivityCoroutinesBinding
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.lib_base.utils.LogUtil
import kotlinx.coroutines.*

/**
 * Kotlin协程的取消
 *
 * @author LTP 2021/9/24
 */
class CoroutinesActivity :
    BaseBindingActivity<ActivityCoroutinesBinding>(R.layout.activity_coroutines) {

    private lateinit var mJob: Job

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CoroutinesActivity::class.java)
        }
    }

    override fun setupViews() {
        mBinding.apply {
            btnStart.setOnClickListener {
                mJob = GlobalScope.launch(Dispatchers.Main) {
                    repeat(100) {
                        LogUtil.d("第${it + 1}次运行协程")
                        delay(1000)
                    }
                }
            }

            btnCancel.setOnClickListener {
                mJob.cancel()
            }
        }
    }
}