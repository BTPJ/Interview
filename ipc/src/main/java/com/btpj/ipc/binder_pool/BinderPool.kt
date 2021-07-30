package com.btpj.ipc.binder_pool

import android.os.IBinder

/**
 * @author LTP  2021/7/30
 */
class BinderPool private constructor() {

    companion object {
        val instance: BinderPool by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { BinderPool() }

        const val BINDER_SECURITY_CENTER = 0
        const val BINDER_COMPUTE = 1
    }

    class BinderPoolImpl : IBinderPool.Stub() {
        override fun queryBinder(binderCode: Int): IBinder? {
            return when (binderCode) {
                BINDER_SECURITY_CENTER -> SecurityCenterImpl()
                BINDER_COMPUTE -> ComputeImpl()
                else -> null
            }
        }
    }
}