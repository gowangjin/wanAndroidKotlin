package com.example.wanandroidkotlin.adapter

import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.gson.DrawersTitleBean
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.BR
import com.example.wanandroidkotlin.databinding.DrawersChildBootomLayoutBinding
import javax.inject.Inject

class DrawersLayoutAdapter @Inject constructor() :
    BaseRecyclerAdapter<DrawersTitleBean, DrawersChildBootomLayoutBinding>(R.layout.drawers_child_bootom_layout,BR.drawers_bean) {
}