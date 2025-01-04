package com.example.module_home.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.listener.IAdapterItemOnClickListener
import com.example.commonlibary.util.LogUtil
import com.example.module_home.R
import com.example.module_home.adapter.HomeArticleListAdapter
import com.example.module_home.databinding.FragmentHomeBinding
import com.youth.banner.listener.OnBannerListener
import dagger.hilt.android.AndroidEntryPoint
import logic.model.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() :BaseFragment<FragmentHomeBinding, HomeViewModel>(){
    private val TAG = "HomeFragment"
    private lateinit var mArticleAdapter:HomeArticleListAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun providerVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bannerLiveData = mViewModel.requestBanner()
        //请求首页文章
        mArticleAdapter = HomeArticleListAdapter(this)
        mArticleAdapter.addItemClickListener(mAdapterChangeListener)
        mBinding.homeRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mBinding.homeRecycler.adapter = mArticleAdapter
        val articleLiveData = mViewModel.requestHomeArticle(0)
        articleLiveData.observe(viewLifecycleOwner){
            LogUtil.d(TAG,"article size ${it.data.articleList.size}")
            onVisibleFailure()
            showHomeArticle(it.data.articleList)
        }
        bannerLiveData.observe(viewLifecycleOwner) {
            onVisibleFailure()
            mArticleAdapter.addBanner(it,mBannerListener)
        }
    }

    /**
     * 展示首页文章
     */
    private fun showHomeArticle(articleDetailList:MutableList<ArticleDetailBean>){
        mArticleAdapter.setData(articleDetailList)
    }

    private fun onVisibleFailure(){
        if(View.GONE != mBinding.ivRequestFailure.visibility){
            mBinding.ivRequestFailure.visibility = View.GONE
        }
    }

    private val mAdapterChangeListener = object : IAdapterItemOnClickListener{
        override fun onItemClick(position: Int) {
            LogUtil.d(TAG, "onItemClick $position")
            val data = mArticleAdapter.getPositionData(position)
            showWebView(data.link)
        }

    }

    private val mBannerListener =
        OnBannerListener<Banner> { data, position ->
            showWebView(data.url)
        }
 }
