package com.btpj.views.eventconflict.same

import android.content.Context
import android.content.Intent
import com.btpj.eventdispatch.R
import com.btpj.eventdispatch.databinding.ActivitySameEventConflictBinding
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * 同向滑动冲突 ScrollView嵌套ListView为例
 *
 * @author LTP 2022/3/4
 */
class SameEventConflictActivity :
    BaseBindingActivity<ActivitySameEventConflictBinding>(R.layout.activity_same_event_conflict) {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SameEventConflictActivity::class.java)
        }
    }


    override fun setupViews() {
    }
}