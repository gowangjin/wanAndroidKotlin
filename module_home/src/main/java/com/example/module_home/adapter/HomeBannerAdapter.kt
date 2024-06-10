package com.example.module_home.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.commonlibary.gson.Banner
import com.youth.banner.adapter.BannerAdapter

class HomeBannerAdapter(private val dataList :List<Banner>) :
    BannerAdapter<Banner, HomeBannerAdapter.BannerViewHolder>(dataList) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: Banner?, position: Int, size: Int) {
        
    }

    class BannerViewHolder(val view : ImageView) : RecyclerView.ViewHolder(view){

    }
}