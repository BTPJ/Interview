package com.btpj.ipc.binder_pool

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BinderPoolService : Service() {

    private val mBinderPool = BinderPool.BinderPoolImpl()

    override fun onBind(intent: Intent): IBinder {
        return mBinderPool
    }
}