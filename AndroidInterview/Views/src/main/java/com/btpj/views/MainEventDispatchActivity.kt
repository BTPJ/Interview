package com.btpj.views

import android.content.Context
import android.content.Intent
import com.btpj.eventdispatch.R
import com.btpj.eventdispatch.databinding.ActivityMainEventDispatchBinding
import com.btpj.views.eventconflict.EventConflictActivity
import com.btpj.views.eventdispatch.EventDispatchActivity
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * 壳工程
 *
 * @author LTP 2021/6/21
 */
class MainEventDispatchActivity :
    BaseBindingActivity<ActivityMainEventDispatchBinding>(R.layout.activity_main_event_dispatch) {

    companion object {
        // 判断是否是作为Application的首页
        private var isIndexPage = true

        fun newIntent(context: Context): Intent {
            isIndexPage = false
            return Intent(context, MainEventDispatchActivity::class.java)
        }
    }

    override fun setupViews() {
        mBinding.apply {
            titleLayout.setBackVisible(!isIndexPage)

            // 事件分发
            btnEventDispatch.setOnClickListener {
                startActivity(EventDispatchActivity.newIntent(this@MainEventDispatchActivity))
            }

            // 不同方向的事件冲突
            btnEventConflict1.setOnClickListener {
                startActivity(EventConflictActivity.newIntent(this@MainEventDispatchActivity))
            }

            // 相同方向的事件冲突
            btnEventConflict2.setOnClickListener {
                startActivity(EventDispatchActivity.newIntent(this@MainEventDispatchActivity))
            }
        }
    }
}