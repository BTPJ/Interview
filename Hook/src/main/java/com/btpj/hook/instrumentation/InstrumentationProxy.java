package com.btpj.hook.instrumentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * InstrumentationProxy代理类对Instrumentation进行Hook
 *
 * @author LTP  2023/5/16
 */
public class InstrumentationProxy extends Instrumentation {

    private final Instrumentation mInstrumentation;
    private final PackageManager mPackageManager;

    private static final String PLACEHOLDER_ACTIVITY_PACKAGE_NAME = "com.btpj.hook.PlaceholderActivity";

    public InstrumentationProxy(Instrumentation mInstrumentation, PackageManager mPackageManager) {
        this.mInstrumentation = mInstrumentation;
        this.mPackageManager = mPackageManager;
    }

    /**
     * 直接在execStartActivity中将要启动的TargetActivity(未注册)替换为占坑的PlaceholderActivity
     */
    @SuppressLint("QueryPermissionsNeeded")
    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        List<ResolveInfo> resolveInfoList = mPackageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (resolveInfoList == null || resolveInfoList.size() == 0) {
            // resolveInfoList为空表示启动的Activity未注册
            intent.putExtra(HookHelper.TARGET_ACTIVITY_NAME, intent.getComponent().getClassName());
            intent.setClassName(who, PLACEHOLDER_ACTIVITY_PACKAGE_NAME);
        }

        try {
            Method execMethod = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            return (ActivityResult) execMethod.invoke(mInstrumentation, who, contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建时当匹配到启动的为PlaceholderActivity时又转化为TargetActivity
     */
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String targetActivityName = intent.getStringExtra(HookHelper.TARGET_ACTIVITY_NAME);
        if (className.equals(PLACEHOLDER_ACTIVITY_PACKAGE_NAME) && !TextUtils.isEmpty(targetActivityName)) {
            return super.newActivity(cl, targetActivityName, intent);
        }
        return super.newActivity(cl, className, intent);
    }
}
