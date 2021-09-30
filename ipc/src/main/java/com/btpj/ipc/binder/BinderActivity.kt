package com.btpj.ipc.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.btpj.ipc.R
import com.btpj.ipc.databinding.ActivityBinderBinding
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * Activity与Service通信（同进程，跨进程会报错）
 *
 * @author LTP 2021/7/29
 */
class BinderActivity : BaseBindingActivity<ActivityBinderBinding>(R.layout.activity_binder) {

    companion object {
        /** 启动的Intent */
        fun newIntent(context: Context): Intent {
            return Intent(context, BinderActivity::class.java)
        }
    }

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
        mBinding.btnGetProgress.setOnClickListener {
            mBinding.tvProgress.text = mBinder.getProgress().toString()
        }
    }
}