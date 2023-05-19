package com.btpj.hook.instrumentation;

import android.app.Instrumentation;
import android.content.Context;

import com.btpj.lib_base.utils.LogUtil;

import java.lang.reflect.Field;

/**
 * Hook帮助类
 *
 * @author LTP  2023/5/16
 */
public class HookHelper {
    public static final String TARGET_ACTIVITY_NAME = "target_activity_name";

    public static void hookInstrumentation(Context context) throws Exception {
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        // 寻找ActivityThread类中的sCurrentActivityThread属性
        Field activityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
        activityThreadField.setAccessible(true);
        Object activityThread = activityThreadField.get(context);

        // 寻找ActivityThread类中的mInstrumentation属性
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);

        LogUtil.d("Hook Instrumentation进ActivityThread成功");
        // 将mInstrumentation属性赋值为InstrumentationProxy对象
        mInstrumentationField.set(activityThread, new InstrumentationProxy((Instrumentation) mInstrumentationField.get(activityThread), context.getPackageManager()));
    }
}
