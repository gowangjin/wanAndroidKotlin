package com.example.module_navi.adapter

import android.content.Context
import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.base.BaseRecyclerViewHolder
import com.example.commonlibary.gson.NaviTreeBean
import com.example.module_navi.BR
import com.example.module_navi.R
import com.example.module_navi.databinding.ItemMenuLayoutBinding
import javax.inject.Inject

class NaviMenuAdapter @Inject constructor() : BaseRecyclerAdapter<NaviTreeBean,ItemMenuLayoutBinding>(
    R.layout.item_menu_layout,BR.data){
    private var mSelectPosition = 0
    private lateinit var mContext: Context
    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        mContext = holder.itemView.context
        holder.itemView.isSelected = position == mSelectPosition

    }

    override fun onItemClick(position: Int) {
        super.onItemClick(position)
        val oldSelectPosition = mSelectPosition
        mSelectPosition = position
        notifyItemChanged(position)
        notifyItemChanged(oldSelectPosition)
    }
}