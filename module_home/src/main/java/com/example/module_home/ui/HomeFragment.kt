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
        bannerLiveData.observe(viewLifecycleOwner) {
           addBanner(it)
        }
        //请求首页文章
        mArticleAdapter = HomeArticleListAdapter()
        mBinding.homeRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mBinding.homeRecycler.adapter = mArticleAdapter
        val articleLiveData = mViewModel.requestHomeArticle(0)
        articleLiveData.observe(viewLifecycleOwner){
            LogUtil.d(TAG,"article size ${it.data.articleList.size}")
            showHomeArticle(it.data.articleList)
        }
    }

    /**
     * 展示首页文章
     */
    private fun showHomeArticle(articleDetailList:MutableList<ArticleDetailBean>){
        mArticleAdapter.setData(articleDetailList)
    }

    /**
     * 展示轮播图
     */
    private fun addBanner(response: Response<List<Banner>>){
        mBinding.homeBanner.addBannerLifecycleObserver(this)
            .setAdapter(object : BannerImageAdapter<Banner>(response.data){
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: Banner?,
                    position: Int,
                    size: Int
                ) {
                    LogUtil.d(TAG,"banner ${data?.imagePath}")
                    holder?.let { it1 ->
                        Glide.with(it1.itemView)
                            .load(data?.imagePath)
                            .into(holder.imageView)
                    }
                }
            })
            .addBannerLifecycleObserver(this)
            .setIndicator(CircleIndicator(context)).start()
    }
}
