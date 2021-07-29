package com.btpj.interview

import android.content.Intent
import com.btpj.lib_base.base.BaseActivity
//import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 壳工程
 *
 * @author LTP 2021/6/21
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun setupViews() {
        // Activity
        btn_activity.setOnClickListener { startActivity(Intent("com.btpj.activity.ORIENTATION")) }

        // Handler
        btn_handler.setOnClickListener {
            startActivity(Intent("com.btpj.handler.HANDLER"))
        }

        // 事件分发
        btn_event.setOnClickListener {
            startActivity(Intent("com.btpj.eventdispatch.EVENT_DISPATCH"))
        }

        // IPC通信
        btn_ipc.setOnClickListener {
//            startActivity(Intent("com.btpj.ipc.BINDER"))
            startActivity(Intent("com.btpj.ipc.MESSENGER"))
        }

        // MVC、MVP、MVVM
        btn_mvc_mvp_mvvm.setOnClickListener {
            // 隐式启动
            startActivity(Intent("com.btpj.mvcmvpmvvm.ACTION_MVVM"))
            // startActivity(Intent("com.btpj.mvcmvpmvvm.ACTION_MVP"))
            // startActivity(Intent("com.btpj.mvcmvpmvvm.ACTION_MVC"))

            // Arouter（此处暂时不兼容AndroidX故暂时直接使用隐式启动）
            //  ARouter.getInstance().build("/MvcMvpMvvm/mvc").navigation()
        }
    }
}