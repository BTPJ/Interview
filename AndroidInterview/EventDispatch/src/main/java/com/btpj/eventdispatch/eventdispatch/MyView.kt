package com.btpj.eventdispatch.eventdispatch

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.btpj.lib_base.utils.LogUtil

/**
 * 自定义View查看事件传递
 *
 * @author LTP 2021/6/24
 */
class MyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtil.w("MyView调用dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> LogUtil.w("MyView调用onTouchEvent的ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> LogUtil.w("MyView调用onTouchEvent的ACTION_MOVE")
            MotionEvent.ACTION_UP -> LogUtil.w("MyView调用onTouchEvent的ACTION_UP")
        }
        return super.onTouchEvent(event)
//        return true
    }
}