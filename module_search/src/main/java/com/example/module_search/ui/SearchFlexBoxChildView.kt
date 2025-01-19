package com.example.module_search.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.commonlibary.gson.SearchHotKeyWord
import com.example.commonlibary.util.LogUtil
import com.example.module_search.BR
import com.example.module_search.R
import com.example.module_search.databinding.SearchFlexBoxChildLayoutBinding
import com.example.module_search.listener.IHotWordOnclickListener

class SearchFlexBoxChildView : ConstraintLayout {
    companion object{
        private const val TAG = "SearchFlexBoxChildView"
    }
    private var mDataBinding : SearchFlexBoxChildLayoutBinding
    private var mListener: IHotWordOnclickListener
    constructor(context: Context,listener:IHotWordOnclickListener) :this(context,null,listener)
    constructor(context: Context, attrs: AttributeSet?,listener:IHotWordOnclickListener) : this(context,attrs,0,listener)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int,listener:IHotWordOnclickListener) : super(context,
        attrs, defStyleAttr){
        mListener = listener
    }
    init {
        val layoutInflater = LayoutInflater.from(context)
        mDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.search_flex_box_child_layout,null,false)
        addView(mDataBinding.root)
    }

    fun setData(data: SearchHotKeyWord){
        LogUtil.d(TAG,"data ${data.name}")
        mDataBinding.setVariable(BR.data,data)
        mDataBinding.setVariable(BR.childView,this)
        mDataBinding.notifyPropertyChanged(BR.data)
    }

    fun onClick(){
        LogUtil.d(TAG,"onClick " + mDataBinding.data)
        val data = mDataBinding.data as SearchHotKeyWord
        mListener.onClick(data)
    }
}