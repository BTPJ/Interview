package com.btpj.hook

import android.content.Context
import android.content.Intent
import com.btpj.hook.databinding.ActivityOccupyBinding
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * 占位的Activity
 *
 * @author LTP 2023/5/15
 */
class OccupyActivity : BaseBindingActivity<ActivityOccupyBinding>(R.layout.activity_occupy) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, OccupyActivity::class.java)
        }
    }

    override fun setupViews() {

    }
}