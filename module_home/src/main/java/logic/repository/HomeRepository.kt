package logic.repository

import androidx.lifecycle.MutableLiveData
import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.ArticleBean
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import com.example.commonlibary.util.LogUtil
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class HomeRepository  @Inject constructor() : BaseRepository() {
    companion object{
        private const val TAG = "HomeRepository"
    }
    fun requestBanner(): MutableLiveData<Response<List<Banner>>> {
        LogUtil.d(TAG, "requestBanner: " )
        val bannerLiveData = MutableLiveData<Response<List<Banner>>>()
        val bannerCall : Call<Response<List<Banner>>> = RetrofitHelper.create<ApiService>().getBanner()
        //异步请求
        bannerCall.enqueue(object : Callback<Response<List<Banner>>>{
            override fun onResponse(
                call: Call<Response<List<Banner>>>,
                response: retrofit2.Response<Response<List<Banner>>>
            ) {
                bannerLiveData.postValue(response.body())
               LogUtil.d(TAG,"onResponse ${response.message()}")
            }

            override fun onFailure(call: Call<Response<List<Banner>>>, t: Throwable) {
                LogUtil.e(TAG,"onFailure ${t.message}")
            }
        })
        return bannerLiveData
    }

    fun requestHomeArticle(page: Int): MutableLiveData<Response<ArticleBean>> {
        LogUtil.d(TAG, "requestHomeArticle: ")
        val articleLiveData = MutableLiveData<Response<ArticleBean>>()
        val articleList : Call<Response<ArticleBean>> = RetrofitHelper.create<ApiService>().getArticle(page)
        articleList.enqueue(object : Callback<Response<ArticleBean>>{
            override fun onResponse(
                call: Call<Response<ArticleBean>>,
                response: retrofit2.Response<Response<ArticleBean>>
            ) {
                articleLiveData.postValue(response.body())
                LogUtil.d(TAG,"onResponse ${response.message()}")
            }

            override fun onFailure(call: Call<Response<ArticleBean>>, t: Throwable) {
                LogUtil.e(TAG,"onFailure ${t.message}")
            }

        })
        return articleLiveData
    }
}