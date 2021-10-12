package com.btpj.dependencies

/**
 * 依赖版本号
 *
 * @author LTP 2021/9/30
 */
object Versions {
    /**----------- Android-------------*/
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"

    const val coreKtx = "1.3.2"
    const val appcompat = "1.2.0"
    const val material = "1.2.1"
    const val constraintlayout = "2.0.4"
    const val junit = "4.13.2"
    const val junitExt = "1.1.2"
    const val espresso = "3.3.0"

    /**----------- Jetpack-------------*/
    const val viewModelKtx = "2.3.1"
    const val liveDataKtx = "2.3.1"
    const val activityKtx = "1.2.3"

    /**---------------Others----------*/
    const val arouterApi = "1.5.2"
    const val arouterCompiler = "1.5.2"

    const val eventbus = "3.2.0"
}

/**
 * 依赖
 *
 * @author LTP 2021/9/30
 */
object Deps {
    /**定义各模块是当作application还是module(组件)*/
    const val isApplication = false

    /**----------- Android-------------*/
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    /**----------- Jetpack----------------*/
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKtx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    /**---------------Others-------------*/
    // Arouter：https://github.com/alibaba/ARouter,这里Arouter没有适配Androidx有问题
    const val arouterApi = "com.alibaba:arouter-api:${Versions.arouterApi}"
    const val arouterCompiler = "com.alibaba:arouter-compiler:${Versions.arouterCompiler}"

    const val eventbus = "org.greenrobot:eventbus:${Versions.eventbus}"
}