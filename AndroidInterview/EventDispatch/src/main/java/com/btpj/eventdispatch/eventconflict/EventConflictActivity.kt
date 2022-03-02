package com.btpj.eventdispatch.eventconflict

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.btpj.eventdispatch.R
import com.btpj.eventdispatch.databinding.ActivityEventConflictBinding
import com.btpj.lib_base.base.BaseBindingActivity
import java.util.*
import kotlin.collections.ArrayList

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