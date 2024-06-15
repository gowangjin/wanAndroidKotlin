package logic.model

import androidx.lifecycle.MutableLiveData
import com.example.commonlibary.base.BaseViewModel
import com.example.commonlibary.gson.ArticleBean
import com.example.commonlibary.gson.Banner
import com.example.commonlibary.gson.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import logic.repository.HomeRepository
import java.util.concurrent.ScheduledFuture
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var mRepository : HomeRepository
    fun requestBanner(): MutableLiveData<Response<List<Banner>>> {
        return mRepository.requestBanner()
    }

    fun requestHomeArticle(page : Int):MutableLiveData<Response<ArticleBean>>{
        return mRepository.requestHomeArticle(page)
    }
}