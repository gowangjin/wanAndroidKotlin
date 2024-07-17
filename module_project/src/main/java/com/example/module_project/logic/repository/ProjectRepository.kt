package com.example.module_project.logic.repository

import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.ArticleBean
import com.example.commonlibary.gson.ProjectTreeBean
import com.example.commonlibary.gson.Response
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import javax.inject.Inject

class ProjectRepository @Inject constructor() : BaseRepository() {
    suspend fun getProjectTree():Response<List<ProjectTreeBean>> {
        return RetrofitHelper.create<ApiService>().getProjectTree()
    }
    suspend fun getProjectArticle(page:Int,cid:Int):Response<ArticleBean>{
        return RetrofitHelper.create<ApiService>().getProjectArticle(page,cid)
    }
}