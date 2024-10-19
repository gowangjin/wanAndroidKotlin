package com.example.commonlibary.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 基础Adapter
 */
open class BaseRecyclerAdapter<Data,VB : ViewDataBinding>(@LayoutRes val itemLayoutId : Int,
                                                          private val variableId : Int) :
    RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    private var mDataList : MutableList<Data> = arrayListOf()
    protected lateinit var mDataBinding: VB
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        mDataBinding = DataBindingUtil.inflate(layoutInflater,itemLayoutId,parent,false)
        return BaseRecyclerViewHolder(mDataBinding.root)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        val data = mDataList[position]
        mDataBinding.setVariable(variableId,data)
        mDataBinding.executePendingBindings()
        onBindViewHolder(data)
    }

    protected open fun onBindViewHolder(data: Data){
        
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Data>){
        this.mDataList.clear()
        this.mDataList.addAll(data)
        notifyDataSetChanged()
    }
}