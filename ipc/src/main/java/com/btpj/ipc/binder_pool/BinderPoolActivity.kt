package com.btpj.ipc.binder_pool

import android.content.Context
import android.content.Intent
import com.btpj.ipc.R
import com.btpj.lib_base.base.BaseActivity
import com.btpj.lib_base.utils.LogUtil

class BinderPoolActivity : BaseActivity(R.layout.activity_binder_pool) {

    companion object {
        /** 启动的Intent */
        fun newIntent(context: Context): Intent {
            return Intent(context, BinderPoolActivity::class.java)
        }
    }

    override fun setupViews() {
        Thread { doWork() }.start()
    }

    private fun doWork() {
        val binderPool = BinderPool.getInstance(this)
        val securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER)
        val securityCenter = ISecurityCenter.Stub.asInterface(securityBinder)
        val msg = "BTPJ"
        val enMsg = securityCenter.encrypt(msg)
        LogUtil.d("加密：$enMsg")
        LogUtil.d("解密：${securityCenter.decrypt(enMsg)}")

        val computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE)
        val compute = ICompute.Stub.asInterface(computeBinder)
        LogUtil.d("3 + 5 = ${compute.add(3, 5)}")
    }
}