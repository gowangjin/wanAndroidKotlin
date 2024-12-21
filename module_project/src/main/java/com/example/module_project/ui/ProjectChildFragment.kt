package com.example.module_project.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.constant.Constant
import com.example.commonlibary.listener.IAdapterItemOnClickListener
import com.example.commonlibary.util.LogUtil
import com.example.module_project.R
import com.example.module_project.adapter.ProjectChildAdapter
import com.example.module_project.databinding.FragmentChildBinding
import com.example.module_project.logic.model.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProjectChildFragment : BaseFragment<FragmentChildBinding,ProjectViewModel>() {
    @Inject
    lateinit var mAdapter: ProjectChildAdapter
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
    private val mAdapterListener : IAdapterItemOnClickListener =
        object : IAdapterItemOnClickListener {
            override fun onItemClick(position: Int) {
                LogUtil.d(TAG,"onItemClick position: $position")
                val data = mAdapter.getPositionData(position)
                showWebView(data.link)
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
        mAdapter.addItemClickListener(mAdapterListener)
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