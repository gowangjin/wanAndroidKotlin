package com.example.module_search.listener

import com.example.commonlibary.gson.SearchHotKeyWord

/**
 * 热词点击回调
 */
interface IHotWordOnclickListener {
    fun onClick(data: SearchHotKeyWord)
}