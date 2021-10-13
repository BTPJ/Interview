package com.btpj.eventdispatch

import android.content.Context
import android.content.Intent
import android.view.MotionEvent
import com.btpj.lib_base.base.BaseActivity
import com.btpj.lib_base.utils.LogUtil
import com.btpj.lib_base.widgets.TitleLayout

/**
 * Android事件分发
 *
 * @author LTP 2021/6/24
 */
class EventDispatchActivity : BaseActivity(R.layout.activity_event_dispatch) {

    companion object {
        // 判断是否是作为Application的首页
        private var isIndexPage = true

        fun newIntent(context: Context): Intent {
            isIndexPage = false
            return Intent(context, EventDispatchActivity::class.java)
        }
    }

    override fun setupViews() {
        findViewById<TitleLayout>(R.id.titleLayout).setBackVisible(!isIndexPage)

//     findViewById<View>(R.id.myView).setOnTouchListener { v, event ->
//         LogUtil.w("MyView调用setOnTouchListener返回true")
//         LogUtil.w(event.toString())
//         return@setOnTouchListener true
//     }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.i("EventDispatchActivity调用dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> LogUtil.i("EventDispatchActivity调用onTouchEvent的ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> LogUtil.i("EventDispatchActivity调用onTouchEvent的ACTION_MOVE")
            MotionEvent.ACTION_UP -> LogUtil.i("EventDispatchActivity调用onTouchEvent的ACTION_UP")
        }
        return super.onTouchEvent(event)
    }
}