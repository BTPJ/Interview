package com.btpj.activity

import android.content.Context
import android.content.Intent
import com.btpj.activity.databinding.ActivityAboutBinding
import com.btpj.activity.launch.AActivity
import com.btpj.activity.orientation.OrientationActivity
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * Activity相关
 *
 * @author LTP 2021/10/12
 */
class AboutActivity : BaseBindingActivity<ActivityAboutBinding>(R.layout.activity_about) {

    companion object {
        /**
         * 启动Activity
         *
         * @param context Context
         */
        fun launch(context: Context) {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
    }

    override fun setupViews() {
        mBinding.apply {
            btnOrientation.setOnClickListener { OrientationActivity.launch(this@AboutActivity) }
            btnLaunch.setOnClickListener { AActivity.launch(this@AboutActivity) }
        }
    }
}