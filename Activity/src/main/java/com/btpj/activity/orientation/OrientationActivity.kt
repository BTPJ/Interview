package com.btpj.activity.orientation

import android.content.res.Configuration
import com.btpj.activity.R
import com.btpj.lib_base.base.BaseActivity
import com.btpj.lib_base.utils.LogUtil

/**
 * Manifests中设Activity置android:configChanges="orientation"查看生命周期的变化
 *    网上答案千奇百怪，亲测Android11是以下结果：
 * 1、不设置Activity的android:configChanges时或者设置android:configChanges="orientation"
 *    或android:configChanges=orientation|keyboardHidden（**Android4.0以后无效**）都跟不设置一样，
 *    切屏都会重新重新创建Activity进而重新回调各个生命周期
 * 2、设置Activity的android:configChanges="orientation|screenSize"时，切屏不会重新调用各个生命周期，
 *    只会执行onConfigurationChanged方法
 *
 * @author LTP 2021/7/9
 */
class OrientationActivity : BaseActivity(R.layout.activity_orientation) {

    override fun setupViews() {
        LogUtil.d("onCreate")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtil.d("onConfigurationChanged")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.d("onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d("onPause")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtil.d("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d("onDestroy")
    }
}