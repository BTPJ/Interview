package com.btpj.handler

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.btpj.lib_base.base.BaseActivity
import com.btpj.lib_base.utils.LogUtil
import kotlinx.android.synthetic.main.activity_handler.*
import java.lang.ref.WeakReference

/**
 * Handler机制
 *
 * @author LTP 2021/6/28
 */
class HandlerActivity : BaseActivity(R.layout.activity_handler) {

    /**  采用静态内部类（kotlin默认内部类就是静态的） + 软引用避免handler造成的内存泄漏 */
    class MyHandler(private val activityReference: WeakReference<Activity>, looper: Looper) :
        Handler(looper) {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            LogUtil.d("当前线程名：${Thread.currentThread().name}")
            // 这里的线程是否是主线程取决于与handler绑定的looper所在的线程
            when (msg.what) {
                1, 2 -> activityReference.get()?.tv_time?.text = msg.obj.toString()
                3 -> LogUtil.d(msg.obj.toString()) // 这里由于是向子线程looper发的消息所以当前线程为子线程，无法更新UI
            }
        }
    }

    override fun setupViews() {
        btn_start.setOnClickListener {
//            startWork()
//            MyThread().start()
            MyThread2().start()
        }
    }

    private fun startWork() {
        val myHandler = MyHandler(WeakReference(this@HandlerActivity), mainLooper)
        Thread {
            val message = Message.obtain().apply {
                what = 1
                obj = "主线程中的Handler向主线程的Looper（即MainLooper）发消息"
            }
            myHandler.sendMessage(message)
        }.start()
    }

    inner class MyThread : Thread() {
        override fun run() {
            super.run()
            val mainLooper = Looper.getMainLooper()
            val myHandler = MyHandler(WeakReference(this@HandlerActivity), mainLooper)
            val obtainMessage =
                myHandler.obtainMessage(2, "子线程中的handler向主线程的Looper（即MainLooper）发消息")
            myHandler.sendMessage(obtainMessage)
        }
    }

    inner class MyThread2 : Thread() {
        override fun run() {
            super.run()
            Looper.prepare()
            val myLooper = Looper.myLooper()!!
            val threadHandler = MyHandler(WeakReference(this@HandlerActivity), myLooper)
            val obtainMessage = threadHandler.obtainMessage(3, "子线程中的handler向子线程的Looper发消息")
            threadHandler.sendMessage(obtainMessage)
            Looper.loop()
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
    // 这里也可以使用removeCallbacksAndMessages取消message的监听防止内存泄漏
//        mHandler.removeCallbacksAndMessages(null)
//    }
}