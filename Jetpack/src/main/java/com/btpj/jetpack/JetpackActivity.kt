package com.btpj.jetpack

import android.content.Context
import android.content.Intent
import com.btpj.jetpack.coroutines.CoroutinesActivity
import com.btpj.lib_base.base.BaseActivity
import com.btpj.lib_base.utils.LogUtil
import kotlinx.android.synthetic.main.activity_jetpack.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * jetpack、kotlin相关
 *
 * @author LTP 2021/9/24
 */
class JetpackActivity : BaseActivity(R.layout.activity_jetpack) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, JetpackActivity::class.java)
        }
    }

    override fun setupViews() {
        btn_coroutines.setOnClickListener {
            startActivity(CoroutinesActivity.newIntent(this))
        }
    }
}