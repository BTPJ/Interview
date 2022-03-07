package com.btpj.views.eventconflict.different

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.HorizontalScrollView
import kotlin.math.abs

/**
 * 外部拦截法解决HorizontalScrollView嵌套ListView的滑动冲突
 * 冲突很小，只会在最最开始斜着滑动时有一些冲突，可以测试在ACTION_MOVE中取消/加上 return false看出来
 *
 * @author LTP  2022/2/28
 */
class MyHorizontalScrollView(context: Context?, attrs: AttributeSet?) :
    HorizontalScrollView(context, attrs) {

    // 记录上一次的横纵坐标
    private var lastX = 0f
    private var lastY = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = ev.x
                lastY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(ev.x - lastX) < abs(ev.y - lastY)) {
                    // 当x方向的滑动距离小于y方向的距离时不拦截
                    return false
                }
            }
        }

        // 这里返回super而不是true是由于HorizontalScrollView本身的onInterceptTouchEvent中包含了大量的滑动逻辑
        return super.onInterceptTouchEvent(ev)
    }
}