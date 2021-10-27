package com.btpj.eventbusdemo

import android.content.Context
import android.content.Intent
import com.btpj.eventbusdemo.databinding.ActivityEventbusBinding
import com.btpj.eventbusdemo.my_eventbus.MyEventBus
import com.btpj.eventbusdemo.my_eventbus.ThreadMode
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.lib_base.utils.LogUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * EventBus使用以及源码分析
 *
 * @author LTP 2021/10/8
 */
class EventBusActivity : BaseBindingActivity<ActivityEventbusBinding>(R.layout.activity_eventbus) {

    companion object {
        /**
         * EventBusActivity启动
         * @param context Context
         */
        fun launch(context: Context) {
            context.startActivity(Intent(context, EventBusActivity::class.java))
        }
    }

    override fun setupViews() {
        EventBus.getDefault().register(this)
        MyEventBus.getDefault().register(this)
        mBinding.apply {
            btnSendSticky.setOnClickListener {
//                EventBus.getDefault().postSticky(Event(1, "我是页面1发过来的粘性消息"))
                MyEventBus.getDefault().postSticky(Event(1, "我是页面1发过来的粘性消息"))
            }

            btnLaunch2.setOnClickListener { EventBus2Activity.launch(this@EventBusActivity) }
        }
    }

    @Subscribe
    fun onReceiveData(event: Event) {
        mBinding.tvText.text = event.data
    }

    /** 这是简易EventBus的回调方法 */
    @com.btpj.eventbusdemo.my_eventbus.Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveMyData(event: Event) {
        LogUtil.d("onReceiveMyData接收的线程名：" + Thread.currentThread().name)
        mBinding.tvText.text = event.data
    }

    /** 这是简易EventBus的回调方法 */
    @com.btpj.eventbusdemo.my_eventbus.Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onReceiveMyData2(event: Event) {
        LogUtil.d("onReceiveMyData2接收的线程名：${Thread.currentThread().name}")
        LogUtil.d("onReceiveMyData2接收到的数据：" + event.data)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        MyEventBus.getDefault().unregister(this)
    }
}