package com.btpj.eventbusdemo

import android.content.Context
import android.content.Intent
import com.btpj.eventbusdemo.databinding.ActivityEventbus2Binding
import com.btpj.eventbusdemo.my_eventbus.MyEventBus
import com.btpj.eventbusdemo.my_eventbus.ThreadMode
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.lib_base.utils.LogUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * 发送eventBus事件
 *
 * @author LTP 2021/10/11
 */
class EventBus2Activity :
    BaseBindingActivity<ActivityEventbus2Binding>(R.layout.activity_eventbus2) {

    companion object {
        /**
         * EventBusActivity启动
         * @param context Context
         */
        fun launch(context: Context) {
            context.startActivity(Intent(context, EventBus2Activity::class.java))
        }
    }

    override fun setupViews() {
        mBinding.apply {
            btnRegister.setOnClickListener {
                if (!EventBus.getDefault().isRegistered(this@EventBus2Activity)) {
//                    EventBus.getDefault().register(this@EventBus2Activity)
                    MyEventBus.getDefault().register(this@EventBus2Activity)
                }
            }
            btnSend.setOnClickListener {
//                EventBus.getDefault().post(Event(2, "我是页面2发送的普通消息"))
                Thread {
//                    MyEventBus.getDefault().post(Event(2, "我是页面2子线程发送的普通消息"))
                }.start()
                MyEventBus.getDefault().post(Event(2, "我是页面2主线程发送的普通消息"))
            }
        }
    }

    @Subscribe(sticky = true)
    fun onReceiveData(event: Event) {
        mBinding.tvStickyText.text = event.data
    }

    /** 这是简易EventBus的回调方法 */
    @com.btpj.eventbusdemo.my_eventbus.Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveMyData(event: Event) {
        LogUtil.d("onReceiveMyData接收的线程名：" + Thread.currentThread().name)
        mBinding.tvStickyText.text = event.data
    }

    /** 这是简易EventBus的回调方法 */
    @com.btpj.eventbusdemo.my_eventbus.Subscribe(threadMode = ThreadMode.MAIN, isSticky = true)
    fun onReceiveStickyData(event: Event) {
        mBinding.tvStickyText.text = event.data
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}