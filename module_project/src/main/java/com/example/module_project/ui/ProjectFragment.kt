package com.example.module_project.ui

import android.os.Bundle
import android.view.View
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.util.LogUtil
import com.example.module_project.R
import com.example.module_project.databinding.FragmentProjectBinding
import com.example.module_project.logic.model.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProjectFragment @Inject constructor(): BaseFragment<FragmentProjectBinding, ProjectViewModel>() {
    companion object{
        private const val TAG = "ProjectFragment"
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun providerVMClass(): Class<ProjectViewModel> {
        return ProjectViewModel::class.java
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.mProjectTreeLiveData.observe(viewLifecycleOwner
        ) { value -> LogUtil.d(TAG, "getProjectTree ${value.size}") }
        mViewModel.getProjectTreeByCoroutine()
    }
}