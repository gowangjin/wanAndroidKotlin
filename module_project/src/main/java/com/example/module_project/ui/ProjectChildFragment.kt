package com.example.module_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.util.LogUtil
import com.example.module_project.BR
import com.example.module_project.R
import com.example.module_project.adapter.ProjectChildAdapter
import com.example.module_project.databinding.FragmentChildBinding
import com.example.module_project.logic.model.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectChildFragment : BaseFragment<FragmentChildBinding,ProjectViewModel>() {
    private val mAdapter = ProjectChildAdapter()
    private var mCurrentId = -1
    private var mCurrentIndex = -1
    companion object {
        private const val TAG = "ProjectChildFragment"
        private const val C_ID: String = "cid"
        private const val INDEX: String = "index"
        fun newInstance(id: Int, i: Int): ProjectChildFragment {
            val fragment = ProjectChildFragment()
            val bundle = Bundle()
            bundle.putInt(C_ID,id)
            bundle.putInt(INDEX,i)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_child
    }

    override fun providerVMClass(): Class<ProjectViewModel> {
        return ProjectViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        registerObserver()
    }

    private fun initView(){
        mBinding.listView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mBinding.listView.adapter = mAdapter
    }

    private fun initData(){
        val bundle = arguments
        bundle?.let {
            mCurrentId = it.getInt(C_ID)
            mCurrentIndex = it.getInt(INDEX)
        }
        LogUtil.d(TAG,"initData mCurrentId:$mCurrentId,mCurrentIndex:$mCurrentIndex")
        mViewModel.getProjectArticleByViewModelScope(1,mCurrentId)
    }

    private fun registerObserver(){
        mViewModel.mProjectArticleLiveData.observe(viewLifecycleOwner){
            mAdapter.setData(it.articleList)
            LogUtil.d(TAG,"articleSize:${it.articleList.size}")
        }
    }
}