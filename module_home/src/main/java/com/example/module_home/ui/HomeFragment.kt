package com.example.module_home.ui

import android.os.Bundle
import android.view.View
import com.example.commonlibary.base.BaseFragment
import com.example.module_home.R
import com.example.module_home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import logic.model.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() :BaseFragment<FragmentHomeBinding, HomeViewModel>(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun providerVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // mViewModel.requestBanner()
    }
}