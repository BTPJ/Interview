package com.btpj.ipc

import android.content.Context
import android.content.Intent
import com.btpj.ipc.aidl.AIDLActivity
import com.btpj.ipc.binder.BinderActivity
import com.btpj.ipc.binder_pool.BinderPoolActivity
import com.btpj.ipc.databinding.ActivityIpcBinding
import com.btpj.ipc.messenger.MessengerActivity
import com.btpj.lib_base.base.BaseBindingActivity

/**
 * IPC多进程通信的方式
 *
 * @author LTP 2021/7/29
 */
class IPCActivity : BaseBindingActivity<ActivityIpcBinding>(R.layout.activity_ipc) {

    companion object {
        // 判断是否是作为Application的首页
        private var isIndexPage = true

        fun newIntent(context: Context): Intent {
            isIndexPage = false
            return Intent(context, IPCActivity::class.java)
        }
    }

    override fun setupViews() {
        mBinding.apply {
            titleLayout.setBackVisible(!isIndexPage)
            btnBinder.setOnClickListener { startActivity(BinderActivity.newIntent(this@IPCActivity)) }
            btnMessenger.setOnClickListener { startActivity(MessengerActivity.newIntent(this@IPCActivity)) }
            btnAidl.setOnClickListener { startActivity(AIDLActivity.newIntent(this@IPCActivity)) }
            btnAidlPool.setOnClickListener { startActivity(BinderPoolActivity.newIntent(this@IPCActivity)) }
        }
    }
}