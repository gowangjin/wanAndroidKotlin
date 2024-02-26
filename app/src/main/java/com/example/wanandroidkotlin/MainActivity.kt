package com.example.wanandroidkotlin

import android.os.Bundle
import com.example.commonlibary.base.BaseActivity
import com.example.wanandroidkotlin.databinding.MainActivityBinding
import com.example.wanandroidkotlin.logic.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding,MainActivityViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.main_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun providerVMClass(): Class<MainActivityViewModel> {
        return MainActivityViewModel::class.java
    }
}