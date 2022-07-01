package com.btpj.ipc.binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.btpj.lib_base.utils.LogUtil

/**
 * Service与Activity通信（同进程）
 *
 * @author LTP 2021/7/29
 */
class BinderService : Service() {

    private val mBinder = DownloadBinder()


    class DownloadBinder : Binder() {
        private var progress = 0

        fun startDownload() {
            Thread {
                while (progress < 100) {
                    Thread.sleep(1000)
                    progress++
                }
            }.start()
        }

        /** 获取下载进度 */
        fun getProgress(): Int {
            return progress
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}