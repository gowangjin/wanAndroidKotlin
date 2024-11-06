package com.example.commonlibary.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.commonlibary.listener.IAdapterItemOnClickListener
import com.example.commonlibary.util.LogUtil

/**
 * 基础Adapter
 */
open class BaseRecyclerAdapter<Data,VB : ViewDataBinding>(@LayoutRes val itemLayoutId : Int,
                                                          private val variableId : Int) :
    RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    companion object{
        private const val TAG = "BaseRecyclerAdapter"
    }
    private var mDataList : MutableList<Data> = arrayListOf()
    private var mItemOnClickListener: IAdapterItemOnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding : VB = DataBindingUtil.inflate(layoutInflater,itemLayoutId,parent,false)
        return BaseRecyclerViewHolder(dataBinding.root)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        val data = mDataList[position]
        onBindViewHolder(holder,data)
        val dataBinding = DataBindingUtil.getBinding<VB>(holder.itemView)
        holder.itemView.setOnClickListener {
            LogUtil.d(TAG,"item onclick $position")
            onItemClick(position)
            mItemOnClickListener?.onItemClick(position) }

        dataBinding?.setVariable(variableId,data)
        dataBinding?.executePendingBindings()
    }

    protected open fun onBindViewHolder(holder: BaseRecyclerViewHolder,data: Data){
        
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Data>){
        this.mDataList.clear()
        this.mDataList.addAll(data)
        notifyDataSetChanged()
    }

    fun addItemClickListener(listener: IAdapterItemOnClickListener){
        mItemOnClickListener = listener
    }
    open fun onItemClick(position: Int){

    }
}