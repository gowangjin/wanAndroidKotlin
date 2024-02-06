package com.example.module_home.ui

import com.example.commonlibary.base.BaseFragment
import com.example.module_home.R
import com.example.module_home.databinding.FragmentHomeBinding
import logic.model.HomeViewModel

class HomeFragment :BaseFragment<FragmentHomeBinding, HomeViewModel>(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun providerVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }
}