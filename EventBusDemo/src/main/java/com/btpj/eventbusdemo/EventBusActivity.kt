package com.btpj.eventbusdemo

import android.content.Context
import android.content.Intent
import com.btpj.eventbusdemo.databinding.ActivityEventbusBinding
import com.btpj.lib_base.base.BaseBindingActivity
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
        mBinding.apply {
            btnSendSticky.setOnClickListener {
                EventBus.getDefault().postSticky(Event(1, "我是页面1发过来的粘性消息"))
            }

            btnLaunch2.setOnClickListener { EventBus2Activity.launch(this@EventBusActivity) }
        }
    }

    @Subscribe
    fun onReceiveData(event: Event) {
        mBinding.tvText.text = event.data
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}