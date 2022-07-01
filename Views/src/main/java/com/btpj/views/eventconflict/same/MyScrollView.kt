package com.btpj.views.eventconflict.same

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ScrollView

/**
 * 自定义ListView解决与ScrollView的滑动冲突
 *
 * @author LTP  2022/3/4
 */
class MyScrollView(context: Context?, attrs: AttributeSet?) : ScrollView(context, attrs) {

    /** scrollView是否滑动到底部 */
    private var mIsScrollViewToBottom = false
    private var mLastY = 0f

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 监听ScrollChangeListener判断ScrollView是否滑动到最底部
            setOnScrollChangeListener { v, _, scrollY, _, _ ->
                mIsScrollViewToBottom = v.height + scrollY >= getChildAt(0).height
            }
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var intercepted = false
        val listView: ListView = (getChildAt(0) as LinearLayout).getChildAt(1) as ListView
        when (ev?.action) {
            MotionEvent.ACTION_MOVE -> {
                intercepted = if (!mIsScrollViewToBottom) {
                    // 如果外层ScrollView没有滑动到最底部，直接由外层ScrollView拦截处理
                    true
                } else {
                    // 外层ScrollView已经滑动到最底部
                    if (!isListViewToTop(listView)) {
                        // 外层ScrollView已经滑动到最底部，且ListView没有处于最顶部，则不拦截交给ListView处理
                        false
                    } else {
                        // 外层ScrollView已经滑动到最底部，且ListView已经处于最顶部
                        // 通过判断当前滑动是否是向上还是向下，向上(ev.y > mLastY)则交给scrollView(intercepted=true)，
                        // 向下(ev.y < mLastY)则交给ListView(intercepted=false)
                        ev.y > mLastY
                    }
                }
            }
        }
        mLastY = ev?.y ?: 0f
        // 必须调用父类的onInterceptTouchEvent，ScrollView内部会有一些滑动处理，不调用会导致无法滑动
        super.onInterceptTouchEvent(ev)
        return intercepted
    }

    /**
     * 判断ListView是否滑动到最顶部
     */
    private fun isListViewToTop(listView: ListView): Boolean {
        if (listView.firstVisiblePosition == 0) {
            val firstChildView = listView.getChildAt(0)
            return firstChildView != null && firstChildView.top >= 0
        }
        return false
    }
}