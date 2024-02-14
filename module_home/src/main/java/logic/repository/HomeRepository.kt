package logic.repository

import com.example.commonlibary.base.BaseRepository
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import com.example.commonlibary.logic.network.ApiService
import com.example.commonlibary.logic.network.RetrofitHelper
import io.reactivex.Observable

class HomeRepository : BaseRepository() {
    fun requestBanner(): Observable<Response<Banner>>{
        return RetrofitHelper.create<ApiService>().getBanner()
    }
}