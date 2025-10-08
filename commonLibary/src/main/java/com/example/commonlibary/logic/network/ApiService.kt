package com.example.commonlibary.logic.network

import com.example.commonlibary.gson.ArticleBean
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.LoginBean
import com.example.commonlibary.gson.NaviTreeBean
import com.example.commonlibary.gson.ProjectTreeBean
import com.example.commonlibary.gson.Response
import com.example.commonlibary.gson.SearchHotKeyWord
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit 的接口文件,建议以具体的功能类名开头,并以Service结尾
 */
interface ApiService {
    /**
     * 首页 轮播图数据
     * 其中返回类型为Call<*>，*是接收数据的类
     */
    @GET("banner/json")
    fun getBanner():Call<Response<List<Banner>>>

    @GET("article/list/{page}/json")
    fun getArticle(@Path("page")page:Int):Call<Response<ArticleBean>>

    /**
     * 项目分类
     * 这里使用协程的挂起函数，就不需要使用Call 回调
     *注意，这里使用到suspend
     */
    @GET("project/tree/json")
    suspend fun getProjectTree():Response<List<ProjectTreeBean>>

    /**
     * 获取项目中的文章
     * https://www.wanandroid.com/project/list/1/json?cid=294
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectArticle(@Path("page")page: Int,@Query("cid")cid:Int):Response<ArticleBean>

    /**
     * https://www.wanandroid.com/navi/json
     * 获取导航数据
     */
    @GET("navi/json")
    suspend fun getNaviTree():Response<MutableList<NaviTreeBean>>

    /**
     * 获取搜索热词
     */
    @GET("hotkey/json")
    suspend fun getSearchHotKeyWords():Response<MutableList<SearchHotKeyWord>>

    /**
     * 搜索
     * https://www.wanandroid.com/article/query/0/json
     */
    @POST("article/query/{pageNum}/json")
    @FormUrlEncoded
    suspend fun getSearchResult(@Path("pageNum") pageNum: Int,@Field("k") k :String):Response<ArticleBean>

    /**
     * https://www.wanandroid.com/user/login
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username : String,@Field("password") password: String):Response<LoginBean>
}