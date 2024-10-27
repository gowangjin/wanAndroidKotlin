package com.example.module_home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.base.BaseRecyclerViewHolder
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import com.example.commonlibary.util.LogUtil
import com.example.module_home.BR
import com.example.module_home.R
import com.example.module_home.databinding.HomeAritcleItemLayoutBinding
import com.example.module_home.databinding.HomeBannerLayoutBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class HomeArticleListAdapter(private var owner: LifecycleOwner) :
    BaseRecyclerAdapter<ArticleDetailBean,HomeAritcleItemLayoutBinding>(
        R.layout.home_aritcle_item_layout, BR.Bean){
    private lateinit var mBannerBinding:HomeBannerLayoutBinding
    private lateinit var mContext:Context
    companion object{
        private const val TAG = "HomeArticleListAdapter"
        private const val ITEM_TYPE_BANNER = 0
        private const val ITEM_TYPE_NORMAL = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        mContext = parent.context
        return if(viewType == ITEM_TYPE_BANNER){
            val layoutInflater = LayoutInflater.from(parent.context)
            mBannerBinding = DataBindingUtil.inflate(layoutInflater,R.layout.home_banner_layout,parent,false)
            BaseRecyclerViewHolder(mBannerBinding.root)
        } else {
            super.onCreateViewHolder(parent, viewType)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            ITEM_TYPE_BANNER
        } else{
            ITEM_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        if(holder.itemViewType == ITEM_TYPE_NORMAL){
            super.onBindViewHolder(holder, position -1)
        }
    }
    /**
     * 展示轮播图
     */
    fun addBanner(response: Response<List<Banner>>){
        mBannerBinding.homeBanner
            .addBannerLifecycleObserver(owner)
            .setAdapter(object : BannerImageAdapter<Banner>(response.data){
                override fun onBindView(holder: BannerImageHolder?, data: Banner?, position: Int, size: Int) {
                    LogUtil.d(TAG,"banner ${data?.imagePath}")
                    holder?.let { it1 ->
                        Glide.with(it1.itemView)
                            .load(data?.imagePath)
                            .into(holder.imageView)
                    }
                }
            })
            .addBannerLifecycleObserver(owner)
            .setIndicator(CircleIndicator(mContext)).start()
    }
    }
