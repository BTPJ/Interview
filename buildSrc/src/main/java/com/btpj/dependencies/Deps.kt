package com.btpj.dependencies

/**
 * 依赖
 *
 * @author LTP 2021/9/30
 */
object Deps {

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
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
}