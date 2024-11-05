package com.example.module_project.ui

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
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
        mViewModel.mProjectTreeLiveData.observe(viewLifecycleOwner)
        {
            initTab(it)
            initViewPager()
        }
        mViewModel.getProjectTreeByViewModelScope() //获取项目分类

    }

    private fun initTab(projectTreeBeanList: List<ProjectTreeBean>){
        mBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                LogUtil.d(TAG, "onTabSelected ${tab?.position}")
                tab?.let {
                    mBinding.projectViewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                LogUtil.d(TAG,"onTabUnselected ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                LogUtil.d(TAG,"onTabReselected ${tab?.position}")
            }

        })
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
    }
    private fun initViewPager(){
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(),mChildFragmentList)
        mBinding.projectViewPager.adapter = viewPagerAdapter
        mBinding.projectViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                LogUtil.d(TAG,"onPageScrolled $position")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                LogUtil.d(TAG,"onPageSelected $position")
                mBinding.tabLayout.selectTab(mBinding.tabLayout.getTabAt(position))
            }
        })
    }
}
