package com.example.module_home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import com.example.commonlibary.util.LogUtil
import com.example.module_home.BR
import com.example.module_home.R
import com.example.module_home.adapter.HomeArticleListAdapter
import com.example.module_home.databinding.FragmentHomeBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
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
        mBinding.homeRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mBinding.homeRecycler.adapter = mArticleAdapter
        val articleLiveData = mViewModel.requestHomeArticle(0)
        articleLiveData.observe(viewLifecycleOwner){
            LogUtil.d(TAG,"article size ${it.data.articleList.size}")
            showHomeArticle(it.data.articleList)
        }
        bannerLiveData.observe(viewLifecycleOwner) {
            mArticleAdapter.addBanner(it)
        }
    }

    /**
     * 展示首页文章
     */
    private fun showHomeArticle(articleDetailList:MutableList<ArticleDetailBean>){
        mArticleAdapter.setData(articleDetailList)
    }

}
