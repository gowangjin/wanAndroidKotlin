package com.example.module_navi.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.commonlibary.gson.NaviTreeBean
import com.example.commonlibary.listener.IAdapterItemOnClickListener
import com.example.commonlibary.util.LogUtil
import com.example.module_navi.R
import com.example.module_navi.adapter.NaviMenuAdapter
import com.example.module_navi.databinding.FragmentNavigationBinding
import com.example.module_navi.logic.model.NaviViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NaviFragment @Inject constructor() : BaseFragment<FragmentNavigationBinding, NaviViewModel>(),IAdapterItemOnClickListener {
    companion object{
        private const val TAG = "NaviFragment"
    }
    @Inject
    lateinit var mMenuAdapter: NaviMenuAdapter
    private val mNaviMenuList:ArrayList<NaviTreeBean> = ArrayList()
    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun providerVMClass(): Class<NaviViewModel> {
        return NaviViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMenuAdapter.addItemClickListener(this)
        mBinding.naviMenuListView.let {
            it.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            it.adapter = mMenuAdapter
            val dividerItemDecoration = DividerItemDecoration(context,LinearLayout.VERTICAL)
            val itemDecorationDrawable = ResourcesCompat.getDrawable(resources,
                com.example.commonlibary.R.drawable.divider_recycler_item,null)
            itemDecorationDrawable?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
            it.addItemDecoration(dividerItemDecoration)
        }
        initData()
    }

    private fun initData() {
        mViewModel.mNaviTreeMutableList.observe(viewLifecycleOwner) {
            mNaviMenuList.clear()
            mNaviMenuList.addAll(it)
            mMenuAdapter.setData(it)
            LogUtil.d(TAG, " $it")
            initFlexBox(it[0].articles)
        }
        mViewModel.getNaviTree()
    }
    private fun initFlexBox(articles: List<ArticleDetailBean>) {
        mBinding.naviFlexBoxLayout.removeAllViews()
        for (articleDetailBean in articles){
            val flexBoxChildView = context?.let { FlexBoxChildView(it) }
            flexBoxChildView?.setData(articleDetailBean)
            mBinding.naviFlexBoxLayout.addView(flexBoxChildView)
        }
    }


    override fun onItemClick(position: Int) {
        LogUtil.d(TAG,"onItemClick $position")
        initFlexBox(mNaviMenuList[position].articles)
    }
}