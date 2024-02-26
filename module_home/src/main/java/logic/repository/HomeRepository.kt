package logic.repository

import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import com.example.commonlibary.util.LogUtil
import io.reactivex.Observable
import javax.inject.Inject

class HomeRepository  @Inject constructor() : BaseRepository() {
    companion object{
        private const val TAG = "HomeRepository"
    }
    fun requestBanner(): Observable<Response<Banner>>{
        LogUtil.d(TAG, "requestBanner: ")
        return RetrofitHelper.create<ApiService>().getBanner()
    }
}