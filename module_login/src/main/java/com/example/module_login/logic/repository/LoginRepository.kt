package com.example.module_login.logic.repository

import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.LoginBean
import com.example.commonlibary.gson.Response
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import javax.inject.Inject

class LoginRepository @Inject constructor() : BaseRepository(){
    suspend fun login(userName:String,password:String): Response<LoginBean> {
        return RetrofitHelper.create<ApiService>().login(userName,password)
    }
}