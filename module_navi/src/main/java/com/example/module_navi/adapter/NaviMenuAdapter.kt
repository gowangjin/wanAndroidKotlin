package com.example.module_navi.adapter

import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.gson.NaviTreeBean
import com.example.module_navi.BR
import com.example.module_navi.R
import com.example.module_navi.databinding.ItemMenuLayoutBinding

class NaviMenuAdapter : BaseRecyclerAdapter<NaviTreeBean,ItemMenuLayoutBinding>(
    R.layout.item_menu_layout,BR.data){
}