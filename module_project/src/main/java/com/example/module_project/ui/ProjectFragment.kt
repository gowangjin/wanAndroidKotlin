package com.example.module_project.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.gson.ArticleBean
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
        mViewModel.mProjectTreeLiveData.observe(viewLifecycleOwner)
        {
            initTab(it)
        }
        mViewModel.getProjectTreeByViewModelScope() //获取项目分类

    }

    private fun initTab(projectTreeBeanList: List<ProjectTreeBean>){
        for(i in projectTreeBeanList.indices){
            val bean = projectTreeBeanList[i]
            val name = bean.name
            val tab = mBinding.tabLayout.newTab()
            tab.text = name
            mBinding.tabLayout.addTab(tab)
            LogUtil.d(TAG,"tab name $name")
            val childFragment = ProjectChildFragment.newInstance(bean.id,i)
            mChildFragmentList.add(childFragment)
        }
        initViewPager()

    }
    private fun initViewPager(){
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(),mChildFragmentList)
        mBinding.projectViewPager.adapter = viewPagerAdapter
    }
}
