package logic.repository

import androidx.lifecycle.MutableLiveData
import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import com.example.commonlibary.util.LogUtil
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class HomeRepository  @Inject constructor() : BaseRepository() {
    companion object{
        private const val TAG = "HomeRepository"
    }
    fun requestBanner(): MutableLiveData<Response<Banner>> {
        LogUtil.d(TAG, "requestBanner: " )
        val bannerLiveData = MutableLiveData<Response<Banner>>()
        val bannerCall : Call<Response<Banner>> = RetrofitHelper.create<ApiService>().getBanner()
        //异步请求
        bannerCall.enqueue(object : Callback<Response<Banner>>{
            override fun onResponse(
                call: Call<Response<Banner>>,
                response: retrofit2.Response<Response<Banner>>
            ) {
                bannerLiveData.postValue(response.body())
               LogUtil.d(TAG,"onResponse ${response.message()}")
            }

            override fun onFailure(call: Call<Response<Banner>>, t: Throwable) {
                LogUtil.e(TAG,"onFailure ${t.message}")
            }
        })
        return bannerLiveData
    }
}