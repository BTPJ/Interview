package com.btpj.hook

import android.content.Context
import android.content.Intent
import com.btpj.hook.databinding.ActivityTargetBinding
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * 目标Activity
 *
 * @author LTP 2023/5/15
 */
class TargetActivity : BaseBindingActivity<ActivityTargetBinding>(R.layout.activity_target) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TargetActivity::class.java)
        }
    }

    override fun setupViews() {

    }
}