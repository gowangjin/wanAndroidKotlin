package com.example.module_navi.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlibary.constant.Constant
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.module_navi.BR
import com.example.module_navi.R
import com.example.module_navi.databinding.FlexBoxChildBinding

class FlexBoxChildView: ConstraintLayout {
    private var mContext: Context = context
    private var mDataBinding : FlexBoxChildBinding
    constructor(context: Context) :this(context,null)
    constructor(context: Context, attrs: AttributeSet?) : this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    init {
        val layoutInflater = LayoutInflater.from(mContext)
        mDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.flex_box_child,null,false)
        mDataBinding.setVariable(BR.ChildView,this)
        addView(mDataBinding.root)
    }
    fun setData(bean: ArticleDetailBean){
        mDataBinding.setVariable(BR.bean,bean)
        mDataBinding.notifyPropertyChanged(BR.bean)
    }

    /**
     * 跳转WebActivity
     */
     fun showWebView(url : String){
        ARouter.getInstance()
            .build(Constant.PATH_WEB)
            .withString(Constant.WEB_LINK,url)
            .navigation()
    }
}