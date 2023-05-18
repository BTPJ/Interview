package com.btpj.hook

import android.app.Application
import android.content.Context
import com.btpj.hook.instrumentation.HookHelper

/**
 * @author LTP  2023/5/18
 */
class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        try {
            HookHelper.hookInstrumentation(base)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}