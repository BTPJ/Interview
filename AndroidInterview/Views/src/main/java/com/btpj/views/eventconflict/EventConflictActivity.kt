package com.btpj.views.eventconflict

import android.content.Context
import android.content.Intent
import com.btpj.eventdispatch.R
import com.btpj.eventdispatch.databinding.ActivityEventConflictBinding
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * 不同滑动方向的事件冲突解决
 *
 * @author LTP 2022/2/28
 */
class EventConflictActivity :
    BaseBindingActivity<ActivityEventConflictBinding>(R.layout.activity_event_conflict) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EventConflictActivity::class.java)
        }
    }

    override fun setupViews() {
    }
}