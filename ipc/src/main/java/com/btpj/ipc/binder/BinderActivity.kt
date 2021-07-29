package com.btpj.ipc.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.btpj.ipc.R
import com.btpj.lib_base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_binder.*

/**
 * Activity与Service通信（同进程，跨进程会报错）
 *
 * @author LTP 2021/7/29
 */
class BinderActivity : BaseActivity(R.layout.activity_binder) {

    private lateinit var mBinder: BinderService.DownloadBinder

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as BinderService.DownloadBinder // 必须是同进程，跨进程这里会类型转换异常
            mBinder.startDownload()
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    override fun setupViews() {
        bindService(Intent(this, BinderService::class.java), mConnection, Context.BIND_AUTO_CREATE)
        btn_getProgress.setOnClickListener { tv_progress.text = mBinder.getProgress().toString() }
    }
}