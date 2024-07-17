package com.example.module_project.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.gson.ProjectTreeBean
import com.example.commonlibary.util.LogUtil
import com.example.module_project.R
import com.example.module_project.adapter.ViewPagerAdapter
import com.example.module_project.databinding.FragmentProjectBinding
import com.example.module_project.logic.model.ProjectViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProjectFragment @Inject constructor(): BaseFragment<FragmentProjectBinding, ProjectViewModel>() {
    companion object{
        private const val TAG = "ProjectFragment"
    }
    private val mChildFragmentList = arrayListOf<ProjectChildFragment>()
    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun providerVMClass(): Class<ProjectViewModel> {
        return ProjectViewModel::class.java
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        mViewModel.mProjectTreeLiveData.observe(viewLifecycleOwner
        ) { value -> LogUtil.d(TAG, "getProjectTree ${value.size}") }
        mViewModel.mProjectTreeLiveData.observe(viewLifecycleOwner
        ) {
            it.let {
                for(element in it){
                    val name = element.name
                    val tab = mBinding.tabLayout.newTab()
                    tab.text = name
                    mBinding.tabLayout.addTab(tab)
                    LogUtil.d(TAG,"tab name $name")
                    val childFragment = ProjectChildFragment()
                    mChildFragmentList.add(childFragment)
                }
                val viewPagerAdapter = ViewPagerAdapter(requireActivity(),mChildFragmentList)
                mBinding.projectViewPager.adapter = viewPagerAdapter
                val selectIndex = mBinding.tabLayout.selectedTabPosition
                val projectTreeBean = it[selectIndex]
                mViewModel.getProjectArticleByViewModelScope(1,projectTreeBean.id)
            }
        }
        mViewModel.getProjectTreeByViewModelScope()

    }
}
