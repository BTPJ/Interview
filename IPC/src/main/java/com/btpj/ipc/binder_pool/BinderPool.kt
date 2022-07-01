package com.btpj.ipc.binder_pool

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.btpj.lib_base.utils.LogUtil
import java.util.concurrent.CountDownLatch

/**
 * Binder线程池
 *
 * @author LTP  2021/7/30
 */
class BinderPool private constructor(context: Context) {

    private var mIBinderPool: IBinderPool? = null
    private lateinit var mConnectBinderPoolCountDownLatch: CountDownLatch
    private var mContext: Context = context.applicationContext

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var mInstance: BinderPool? = null

        fun getInstance(context: Context): BinderPool {
            return mInstance ?: synchronized(BinderPool::class.java) {
                mInstance ?: BinderPool(context)
            }
        }

        const val BINDER_SECURITY_CENTER = 0
        const val BINDER_COMPUTE = 1
    }

    /** Binder自动重连 */
    private val mBinderPoolDeathRecipient: IBinder.DeathRecipient =
        object : IBinder.DeathRecipient {
            override fun binderDied() {
                mIBinderPool?.asBinder()?.unlinkToDeath(this, 0)
                mIBinderPool = null
                connectBinderPoolService()
            }
        }

    private val mBinderPoolConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mIBinderPool = IBinderPool.Stub.asInterface(service)
            mIBinderPool?.asBinder()?.linkToDeath(mBinderPoolDeathRecipient, 0)
            mConnectBinderPoolCountDownLatch.countDown()
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    init {
        connectBinderPoolService()
    }

    fun queryBinder(binderCode: Int): IBinder? {
        return if (mIBinderPool == null) null else mIBinderPool!!.queryBinder(binderCode)
    }

    @Synchronized
    private fun connectBinderPoolService() {
        mConnectBinderPoolCountDownLatch = CountDownLatch(1)
        mContext.bindService(
            Intent(mContext, BinderPoolService::class.java),
            mBinderPoolConnection,
            Context.BIND_AUTO_CREATE
        )
        mConnectBinderPoolCountDownLatch.await()
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