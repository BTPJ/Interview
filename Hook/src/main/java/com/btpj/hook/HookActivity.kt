package com.btpj.hook

import android.content.Context
import android.content.Intent
import com.btpj.hook.databinding.ActivityHookBinding
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * 主Activity
 *
 * @author LTP 2023/5/15
 */
class HookActivity : BaseBindingActivity<ActivityHookBinding>(R.layout.activity_hook) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HookActivity::class.java)
        }
    }

    override fun setupViews() {
        mBinding.btnNav.setOnClickListener {
            startActivity(OccupyActivity.newIntent(this))
        }
    }
}