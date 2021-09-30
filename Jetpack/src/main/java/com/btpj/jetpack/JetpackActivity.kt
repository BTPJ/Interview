package com.btpj.jetpack

import android.content.Context
import android.content.Intent
import android.widget.Button
import com.btpj.jetpack.coroutines.CoroutinesActivity
import com.btpj.lib_base.base.BaseActivity

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
        findViewById<Button>(R.id.btn_coroutines).setOnClickListener {
            startActivity(CoroutinesActivity.newIntent(this))
        }
    }
}