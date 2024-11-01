package com.example.module_navi.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.util.LogUtil
import com.example.module_navi.R
import com.example.module_navi.adapter.NaviMenuAdapter
import com.example.module_navi.databinding.FragmentNavigationBinding
import com.example.module_navi.logic.model.NaviViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NaviFragment @Inject constructor() : BaseFragment<FragmentNavigationBinding, NaviViewModel>() {
    companion object{
        private const val TAG = "NaviFragment"
    }
    private val mMenuAdapter = NaviMenuAdapter()
    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun providerVMClass(): Class<NaviViewModel> {
        return NaviViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.naviMenuListView.let {
            it.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            it.adapter = mMenuAdapter
        }
        initData()
    }

    private fun initData() {
        mViewModel.mNaviTreeMutableList.observe(viewLifecycleOwner) {
            mMenuAdapter.setData(it)
            LogUtil.d(TAG, " $it")
        }
        mViewModel.getNaviTree()
    }
}