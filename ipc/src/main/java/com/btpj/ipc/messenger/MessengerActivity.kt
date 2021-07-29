package com.btpj.ipc.messenger

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import com.btpj.ipc.MyConstants
import com.btpj.ipc.R
import com.btpj.lib_base.base.BaseActivity
import com.btpj.lib_base.utils.LogUtil

/**
 * 采用Messenger的方式跨进程通信 —— 客户端
 *
 * @author LTP 2021/7/29
 */
class MessengerActivity : BaseActivity(R.layout.activity_messenger) {

    private val mReplyMessenger = Messenger(ReplyHandler())

    class ReplyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MyConstants.MSG_FROM_SERVER -> LogUtil.d("客户端收到回复消息：${msg.data.getString("reply")}")
            }
        }
    }

    private lateinit var mMessenger: Messenger

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMessenger = Messenger(service)
            val msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT)
            msg.data = Bundle().apply { putString("msg", "我是客户端正在向服务端发送消息") }
            msg.replyTo = mReplyMessenger
            mMessenger.send(msg)
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    override fun setupViews() {
        bindService(Intent(this, MessengerService::class.java), mConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
    }
}
