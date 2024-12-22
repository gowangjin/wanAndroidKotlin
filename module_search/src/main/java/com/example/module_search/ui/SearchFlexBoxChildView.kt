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

class SearchFlexBoxChildView : ConstraintLayout {
    companion object{
        private const val TAG = "SearchFlexBoxChildView"
    }
    private var mDataBinding : SearchFlexBoxChildLayoutBinding
    constructor(context: Context) :this(context,null)
    constructor(context: Context, attrs: AttributeSet?) : this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs, defStyleAttr)
    init {
        val layoutInflater = LayoutInflater.from(context)
        mDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.search_flex_box_child_layout,null,false)
        addView(mDataBinding.root)
    }

    fun setData(data: SearchHotKeyWord){
        LogUtil.d(TAG,"data ${data.name}")
        mDataBinding.setVariable(BR.data,data)
        mDataBinding.notifyPropertyChanged(BR.data)
    }

}