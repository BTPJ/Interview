package com.btpj.ipc.messenger

import android.app.Service
import android.content.Intent
import android.os.*
import com.btpj.ipc.MyConstants
import com.btpj.lib_base.utils.LogUtil

/**
 * 采用Messenger的方式跨进程通信 —— 服务端
 *
 * @author LTP 2021/7/29
 */
class MessengerService : Service() {

    private class MessengerHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MyConstants.MSG_FROM_CLIENT -> {
                    LogUtil.d("服务端从客户端收到消息:${msg.data.getString("msg")}")
                    val replyMessenger = msg.replyTo
                    val replyMsg = Message.obtain(null, MyConstants.MSG_FROM_SERVER)
                    replyMsg.data = Bundle().apply { putString("reply", "消息已收到") }
                    replyMessenger.send(replyMsg)
                }
            }
        }
    }

    private val mMessenger = Messenger(MessengerHandler())

    override fun onBind(intent: Intent): IBinder {
        return mMessenger.binder
    }
}