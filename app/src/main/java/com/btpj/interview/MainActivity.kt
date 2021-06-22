package com.btpj.interview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 壳工程
 *
 * @author LTP 2021/6/21
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        // Handler
        btn_handler.setOnClickListener {
//            startActivity(HandlerActivity.newIntent(this))
        }

        // 时间分发
        btn_event.setOnClickListener {
//            startActivity(EventActivity.newIntent(this))
        }

        // MVC、MVP、MVVM
        btn_mvc_mvp_mvvm.setOnClickListener {
            // 隐式启动
            val intent = Intent()
            //  intent.action = "com.btpj.mvcmvpmvvm.ACTION_MVC"
            // intent.action = "com.btpj.mvcmvpmvvm.ACTION_MVP"
            intent.action = "com.btpj.mvcmvpmvvm.ACTION_MVVM"
            startActivity(intent)

            // Arouter
            //  ARouter.getInstance().build("/MvcMvpMvvm/mvc").navigation()
        }
    }
}