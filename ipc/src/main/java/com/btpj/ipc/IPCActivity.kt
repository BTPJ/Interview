package com.btpj.ipc

import android.content.Context
import android.content.Intent
import com.btpj.ipc.aidl.AIDLActivity
import com.btpj.ipc.binder.BinderActivity
import com.btpj.ipc.binder_pool.BinderPoolActivity
import com.btpj.ipc.messenger.MessengerActivity
import com.btpj.lib_base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ipc.*

/**
 * IPC多进程通信的方式
 *
 * @author LTP 2021/7/29
 */
class IPCActivity : BaseActivity(R.layout.activity_ipc) {

    companion object {
        // 判断是否是作为Application的首页
        private var isIndexPage = true

        fun newIntent(context: Context): Intent {
            isIndexPage = false
            return Intent(context, IPCActivity::class.java)
        }
    }

    override fun setupViews() {
        titleLayout.setBackVisible(!isIndexPage)

        btn_binder.setOnClickListener { startActivity(BinderActivity.newIntent(this)) }
        btn_messenger.setOnClickListener { startActivity(MessengerActivity.newIntent(this)) }
        btn_aidl.setOnClickListener { startActivity(AIDLActivity.newIntent(this)) }
        btn_aidlPool.setOnClickListener { startActivity(BinderPoolActivity.newIntent(this)) }
    }
}