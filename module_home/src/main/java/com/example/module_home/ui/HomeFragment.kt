package com.example.module_home.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import com.example.commonlibary.util.LogUtil
import com.example.module_home.R
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
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun providerVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bannerLiveData = mViewModel.requestBanner()
        bannerLiveData.observe(viewLifecycleOwner
        ) {
            mBinding.homeBanner.addBannerLifecycleObserver(this)
                .setAdapter(object : BannerImageAdapter<Banner>(it.data){
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
}
