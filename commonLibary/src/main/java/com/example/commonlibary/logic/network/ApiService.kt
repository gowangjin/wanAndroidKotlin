package com.example.commonlibary.logic.network

import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit 的接口文件,建议以具体的功能类名开头,并以Service结尾
 */
interface ApiService {
    /**
     * 首页 轮播图数据
     * 其中返回类型为Call<*>，*是接收数据的类
     */
    @GET("banner/json")
    fun getBanner():Call<Response<Banner>>
}