package com.btpj.ipc.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.os.SystemClock
import com.btpj.lib_base.utils.LogUtil
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

class BookManagerService : Service() {
    /** 使用CopyOnWriteArrayList支持并发读写 */
    private val mBookList = CopyOnWriteArrayList<Book>()
    private val mListenerList = RemoteCallbackList<IOnNewBookArrivedListener>()
    private val mServiceDestroyed = AtomicBoolean(false)

    private val mBinder = object : IBookManager.Stub() {
        override fun getBookList(): MutableList<Book> {
            return mBookList
        }

        override fun addBook(book: Book?) {
            mBookList.add(book)
        }

        override fun registerListener(listener: IOnNewBookArrivedListener?) {
            mListenerList.register(listener)
        }

        override fun unRegisterListener(listener: IOnNewBookArrivedListener?) {
            mListenerList.unregister(listener)
            val size = mListenerList.beginBroadcast()
            LogUtil.d("当前订阅者：$size")
            mListenerList.finishBroadcast()
        }
    }

    override fun onCreate() {
        super.onCreate()
        mBookList.apply {
            add(Book(0, "Android"))
            add(Book(1, "Java"))
        }
        Thread {
            while (!mServiceDestroyed.get()) {
                Thread.sleep(1000)
                val newBook = Book(mBookList.size, "书籍${mBookList.size + 1}")
                mBookList.add(newBook)
                onNotifyAllRegister(newBook)
            }
        }.start()
    }

    private fun onNotifyAllRegister(newBook: Book) {
        val size = mListenerList.beginBroadcast()
        for (i in 0 until size) {
            mListenerList.getBroadcastItem(i).onNewBookArrived(newBook)
        }
        mListenerList.finishBroadcast()
    }

    override fun onDestroy() {
        super.onDestroy()
        mServiceDestroyed.set(true)
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}