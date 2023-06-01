package com.btpj.androidinterview

import com.btpj.activity.AboutActivity
import com.btpj.eventbusdemo.EventBusActivity
import com.btpj.views.MainViewsActivity
import com.btpj.handler.HandlerActivity
import com.btpj.hook.HookActivity
import com.btpj.interview.R
import com.btpj.interview.databinding.ActivityMainBinding
import com.btpj.ipc.IPCActivity
//import com.btpj.ipc.IPCActivity
import com.btpj.jetpack.JetpackActivity
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.mvcmvpmvvm.StructureActivity

//import com.alibaba.android.arouter.launcher.ARouter

/**
 * 壳工程
 *
 * @author LTP 2021/6/21
 */
class MainActivity : BaseBindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun setupViews() {
        mBinding.apply {
            // Activity
            btnActivity.setOnClickListener { AboutActivity.launch(this@MainActivity) }

            // Handler
            btnHandler.setOnClickListener {
                startActivity(HandlerActivity.newIntent(this@MainActivity))
            }

            // 事件分发
            btnEvent.setOnClickListener {
                startActivity(MainViewsActivity.newIntent(this@MainActivity))
            }

            // IPC通信
            btnIpc.setOnClickListener {
                startActivity(IPCActivity.newIntent(this@MainActivity))
            }

            // MVC、MVP、MVVM
            btnMvcMvpMvvm.setOnClickListener {
                startActivity(StructureActivity.newIntent(this@MainActivity))

                // Arouter（此处暂时不兼容AndroidX）
                //  ARouter.getInstance().build("/MvcMvpMvvm/mvc").navigation()
            }

            // Kotlin & 协程
            btnKotlinJetpack.setOnClickListener {
                startActivity(JetpackActivity.newIntent(this@MainActivity))
            }

            // EventBus
            btnEventBus.setOnClickListener {
                startActivity(EventBusActivity.newIntent(this@MainActivity))
            }

            // hook
            btnHook.setOnClickListener {
                startActivity(HookActivity.newIntent(this@MainActivity))
            }
        }
    }
}