package com.example.commonlibary.logic.network

import com.example.commonlibary.constant.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {
    private val mRetrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //addConverterFactory 指定Retrofit在解析数据时所使用的转换库
        .build()

    fun <T> create(serviceClass: Class<T>) : T = mRetrofit.create(serviceClass)

    /**
     * inline Kotlin编译器会将内联函数中的代码在编译的时候自动替换到用它的地方
     *reified 关键字,用于修饰内联函数中的类型参数.
     */
    inline fun <reified  T> create() : T = create(T::class.java)

}