package com.btpj.ipc

import com.btpj.ipc.binder.BinderActivity
import com.btpj.ipc.messenger.MessengerActivity
import com.btpj.lib_base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ipc.*

/**
 * IPC多进程通信的方式
 *
 * @author LTP 2021/7/29
 */
class IPCActivity : BaseActivity(R.layout.activity_ipc) {

    override fun setupViews() {
        btn_binder.setOnClickListener { startActivity(BinderActivity.newIntent(this)) }
        btn_messenger.setOnClickListener { startActivity(MessengerActivity.newIntent(this)) }
    }
}