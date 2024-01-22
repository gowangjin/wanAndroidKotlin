package com.example.module_home.fragment

import com.example.commonlibary.base.BaseFragment
import com.example.module_home.R
import com.example.module_home.databinding.FragmentHomeBinding

class HomeFragment :BaseFragment<FragmentHomeBinding>(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}