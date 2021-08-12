package com.btpj.handler

import android.app.Activity
import android.content.Context
import android.content.Intent
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

    companion object {
        // 判断是否是作为Application的首页
        private var isIndexPage = true

        fun newIntent(context: Context): Intent {
            isIndexPage = false
            return Intent(context, HandlerActivity::class.java)
        }
    }

    /**  采用静态内部类（kotlin默认内部类就是静态的） + 软引用避免handler造成的内存泄漏 */
    class MyHandler(private val activityReference: WeakReference<Activity>, looper: Looper) :
        Handler(looper) {

        /** 这个回调具体执行的线程取决于handler初始化时与handler绑定的looper所在的线程 */
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
        titleLayout.setBackVisible(!isIndexPage)
        btn_start.setOnClickListener {
//            startWork()
//            MyThread().start()
            MyThread2().start()
        }

        // 这里调用looper.quit释放MyThread2，quit会回收消息并调用nativeWake,nativeWake会唤醒nativePollOnce
        // 从而判断message==null退出消息循环
        btn_quit.setOnClickListener { myLooper.quit() }
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

    private lateinit var myLooper: Looper

    inner class MyThread2 : Thread() {
        override fun run() {
            super.run()
            Looper.prepare()
            myLooper = Looper.myLooper()!!
            val threadHandler = MyHandler(WeakReference(this@HandlerActivity), myLooper)
            val obtainMessage = threadHandler.obtainMessage(3, "子线程中的handler向子线程的Looper发消息")
            threadHandler.sendMessage(obtainMessage)
            Looper.loop()
            LogUtil.d("MyThread2执行完毕")
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//     // 这里也可以使用removeCallbacksAndMessages取消handler对message的监听（message.recycleUnchecked）防止内存泄漏
//        mHandler.removeCallbacksAndMessages(null)
//    }
}