package com.btpj.interview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.btpj.lib_base.base.BaseActivity
import com.btpj.lib_base.utils.StatusBarUtil
//import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 壳工程
 *
 * @author LTP 2021/6/21
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun setupViews() {
        // Handler
        btn_handler.setOnClickListener {
//             startActivity(Intent("com.btpj.mvcmvpmvvm.EVENT_DISPATCH"))
        }

        // 事件分发
        btn_event.setOnClickListener {
             startActivity(Intent("com.btpj.eventdispatch.EVENT_DISPATCH"))
        }

        // MVC、MVP、MVVM
        btn_mvc_mvp_mvvm.setOnClickListener {
            // 隐式启动
            startActivity(Intent("com.btpj.mvcmvpmvvm.ACTION_MVVM"))
            // startActivity(Intent("com.btpj.mvcmvpmvvm.ACTION_MVP"))
            // startActivity(Intent("com.btpj.mvcmvpmvvm.ACTION_MVC"))

            // Arouter
            //  ARouter.getInstance().build("/MvcMvpMvvm/mvc").navigation()
        }
    }
}