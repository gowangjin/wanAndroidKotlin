package com.example.module_home.ui

import android.os.Bundle
import com.example.commonlibary.base.BaseFragment
import com.example.module_home.R
import com.example.module_home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import logic.model.HomeViewModel
@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding, HomeViewModel>(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun providerVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.requestBanner()
    }
}