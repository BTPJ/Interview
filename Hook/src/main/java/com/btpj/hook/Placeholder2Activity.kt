package com.btpj.hook

import android.content.Context
import android.content.Intent
import com.btpj.hook.databinding.ActivityPlaceholderBinding
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * 占位的Activity
 *
 * @author LTP 2023/5/15
 */
class Placeholder2Activity : BaseBindingActivity<ActivityPlaceholderBinding>(R.layout.activity_placeholder) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, Placeholder2Activity::class.java)
        }
    }

    override fun setupViews() {

    }
}