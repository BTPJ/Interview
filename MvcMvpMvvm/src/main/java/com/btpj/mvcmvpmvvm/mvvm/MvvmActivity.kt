package com.btpj.mvcmvpmvvm.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.btpj.mvcmvpmvvm.R
import com.btpj.mvcmvpmvvm.databinding.ActivityMvvmBinding
import com.btpj.mvcmvpmvvm.mvvm.viewModel.MvvmViewModel

/**
 * MVVM模式
 * 优点：View和Model使用DataBinding来进行双向绑定，一方的改变都会影响另一方，开发者不用再去手动修改UI的数据。项目耦合更低
 * 缺点：DataBinding不排查问题
 *
 * @author LTP 2021/6/22
 */
class MvvmActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMvvmBinding
    private val mViewModel: MvvmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
    }
}