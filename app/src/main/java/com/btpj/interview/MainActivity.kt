package com.btpj.interview

import android.widget.Button
import com.btpj.activity.orientation.OrientationActivity
import com.btpj.eventdispatch.EventDispatchActivity
import com.btpj.handler.HandlerActivity
import com.btpj.ipc.IPCActivity
import com.btpj.jetpack.JetpackActivity
import com.btpj.lib_base.base.BaseActivity
import com.btpj.mvcmvpmvvm.StructureActivity

//import com.alibaba.android.arouter.launcher.ARouter

/**
 * 壳工程
 *
 * @author LTP 2021/6/21
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun setupViews() {
        // Activity
        findViewById<Button>(R.id.btn_activity).setOnClickListener {
            startActivity(
                OrientationActivity.newIntent(this)
            )
        }

        // Handler
        findViewById<Button>(R.id.btn_handler).setOnClickListener {
            startActivity(HandlerActivity.newIntent(this))
        }

        // 事件分发
        findViewById<Button>(R.id.btn_event).setOnClickListener {
            startActivity(EventDispatchActivity.newIntent(this))
        }

        // IPC通信
        findViewById<Button>(R.id.btn_ipc).setOnClickListener {
            startActivity(IPCActivity.newIntent(this))
        }

        // MVC、MVP、MVVM
        findViewById<Button>(R.id.btn_mvc_mvp_mvvm).setOnClickListener {
            startActivity(StructureActivity.newIntent(this))

            // Arouter（此处暂时不兼容AndroidX）
            //  ARouter.getInstance().build("/MvcMvpMvvm/mvc").navigation()
        }

        // Kotlin & 协程
        findViewById<Button>(R.id.btn_kotlin_jetpack).setOnClickListener {
            startActivity(JetpackActivity.newIntent(this))
        }
    }
}