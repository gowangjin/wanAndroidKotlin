package com.example.commonlibary.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerAdapter<Data,VB : ViewDataBinding>(@LayoutRes val itemLayoutId : Int,val variableId : Int) :
    RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    private  var mDataList : List<Data> ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding : VB = DataBindingUtil.inflate(layoutInflater,itemLayoutId,parent,false)
        return BaseRecyclerViewHolder(dataBinding.root)
    }

    override fun getItemCount(): Int {
        return mDataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        val binding : VB? = DataBindingUtil.getBinding(holder.itemView)
        val data = mDataList?.get(position)
        binding?.setVariable(variableId,data)
    }
}