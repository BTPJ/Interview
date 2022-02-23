package com.btpj.eventdispatch

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import com.btpj.lib_base.utils.LogUtil

/**
 * 自定义一个ViewGroup
 *
 * @author LTP 2021/6/24
 */
class MyViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    /**
     * 1、判断是否需要拦截事件
     * 2、去当前ViewGroup中找到用户真正点击的View
     * 3、分发事件到View上
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.d("MyViewGroup调用dispatchTouchEvent")
//        return super.dispatchTouchEvent(ev)
        return true
//        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.d("MyViewGroup调用onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
//        return true
//        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> LogUtil.d("MyViewGroup调用onTouchEvent的ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> LogUtil.d("MyViewGroup调用onTouchEvent的ACTION_MOVE")
            MotionEvent.ACTION_UP -> LogUtil.d("MyViewGroup调用onTouchEvent的ACTION_UP")
        }
        return super.onTouchEvent(event)
//        return true
    }
}