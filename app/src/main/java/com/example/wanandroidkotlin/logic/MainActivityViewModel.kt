package com.example.wanandroidkotlin.logic

import android.content.Context
import com.example.commonlibary.base.BaseViewModel
import com.example.commonlibary.gson.DrawersTitleBean
import com.example.commonlibary.util.LogUtil
import com.example.wanandroidkotlin.R

class MainActivityViewModel :BaseViewModel() {
    companion object{
        private const val TAG = "MainActivityViewModel"
    }
    /**
     * 获取抽屉展示数据
     */
    fun getDrawersListData(context: Context) : MutableList<DrawersTitleBean>{
        val titleArray = context.resources.getStringArray(R.array.drawers_left_title)
        val drawableArray = context.resources.obtainTypedArray(R.array.drawers_drawable)
        val mData = arrayListOf<DrawersTitleBean>()
        for (i in titleArray.indices){
            val image = drawableArray.getDrawable(i)
            image?.let {
                val bean = DrawersTitleBean(image,titleArray[i])
                mData.add(bean)
            }
        }
        drawableArray.recycle()
        LogUtil.d(TAG,"getDrawersListData " + mData.size)
        return mData
    }
}