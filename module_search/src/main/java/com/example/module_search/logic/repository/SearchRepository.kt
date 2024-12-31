package com.example.module_search.logic.repository

import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.ArticleBean
import com.example.commonlibary.gson.Response
import com.example.commonlibary.gson.SearchHotKeyWord
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import javax.inject.Inject

class SearchRepository @Inject constructor() : BaseRepository(){
    suspend fun getSearchHotKeyWords():Response<MutableList<SearchHotKeyWord>>{
        return RetrofitHelper.create<ApiService>().getSearchHotKeyWords()
    }

    suspend fun getSearchResult(keyWord:String,pageNum:Int):Response<ArticleBean>{
        return RetrofitHelper.create<ApiService>().getSearchResult(pageNum,keyWord)
    }
}