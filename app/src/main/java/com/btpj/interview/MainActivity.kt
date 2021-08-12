package com.btpj.interview

import com.btpj.activity.orientation.OrientationActivity
import com.btpj.eventdispatch.EventDispatchActivity
import com.btpj.handler.HandlerActivity
import com.btpj.ipc.IPCActivity
import com.btpj.lib_base.base.BaseActivity
import com.btpj.mvcmvpmvvm.StructureActivity
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
        btn_activity.setOnClickListener { startActivity(OrientationActivity.newIntent(this)) }

        // Handler
        btn_handler.setOnClickListener {
            startActivity(HandlerActivity.newIntent(this))
        }

        // 事件分发
        btn_event.setOnClickListener {
            startActivity(EventDispatchActivity.newIntent(this))
        }

        // IPC通信
        btn_ipc.setOnClickListener {
            startActivity(IPCActivity.newIntent(this))
        }

        // MVC、MVP、MVVM
        btn_mvc_mvp_mvvm.setOnClickListener {
            startActivity(StructureActivity.newIntent(this))

            // Arouter（此处暂时不兼容AndroidX）
            //  ARouter.getInstance().build("/MvcMvpMvvm/mvc").navigation()
        }
    }
}