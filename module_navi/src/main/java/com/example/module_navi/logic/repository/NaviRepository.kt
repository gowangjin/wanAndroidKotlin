package com.example.module_navi.logic.repository

import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.NaviTreeBean
import com.example.commonlibary.gson.Response
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import javax.inject.Inject

class NaviRepository @Inject constructor() : BaseRepository() {
    suspend fun getNaviTree(): Response<MutableList<NaviTreeBean>> {
        return RetrofitHelper.create<ApiService>().getNaviTree()
    }
}