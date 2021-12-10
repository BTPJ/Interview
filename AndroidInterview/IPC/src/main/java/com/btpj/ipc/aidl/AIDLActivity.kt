package com.btpj.ipc.aidl

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import com.btpj.ipc.MyConstants
import com.btpj.ipc.R
import com.btpj.ipc.databinding.ActivityAidlBinding
import com.btpj.lib_base.base.BaseBindingActivity
import com.btpj.lib_base.utils.LogUtil
import java.lang.ref.WeakReference

/**
 * 使用AIDL进行进程间通信
 * 1、创建Service和AIDL接口
 * 2、在Service中创建一个继承AIDL中的Sub类并实现其抽象方法的对象，并在onBind方法中返回该对象
 * 3、在客户端绑定服务传入ServiceConnection的实现类，在onServiceConnected回调方法中即可获取服务进程的数据
 *
 * @author LTP 2021/7/30
 */
class AIDLActivity : BaseBindingActivity<ActivityAidlBinding>(R.layout.activity_aidl) {

    private lateinit var mBookManager: IBookManager

    companion object {
        /** 启动的Intent */
        fun newIntent(context: Context): Intent {
            return Intent(context, AIDLActivity::class.java)
        }
    }

    private class MyHandler(val activity: WeakReference<AIDLActivity>) :
        Handler(Looper.getMainLooper()) {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MyConstants.MSG_NEW_BOOK_RECEIVED -> {
                    activity.get()?.mBinding?.tvResult?.text = "到的新书：${msg.obj}"
                    LogUtil.d("到的新书：${msg.obj}")
                }
            }
        }
    }

    private val mListener = object : IOnNewBookArrivedListener.Stub() {
        override fun onNewBookArrived(book: Book?) {
            MyHandler(WeakReference(this@AIDLActivity)).obtainMessage(
                MyConstants.MSG_NEW_BOOK_RECEIVED,
                book
            ).sendToTarget()
        }
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBookManager = IBookManager.Stub.asInterface(service)
            LogUtil.d("bookList：${mBookManager.bookList}")
            mBookManager.addBook(Book(3, "IOS"))
            LogUtil.d("bookList：${mBookManager.bookList}")
            mBookManager.registerListener(mListener)
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    override fun setupViews() {
        bindService(
            Intent(this, BookManagerService::class.java),
            mConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBookManager.asBinder()!!.isBinderAlive) {
            mBookManager.unRegisterListener(mListener)
        }
        unbindService(mConnection)
    }
}