package com.example.commonlibary.gson

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("data") val data: List<T>,
    val errorCode: Int,
    val errorMsg: String)

/**
 * 轮播图
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)
