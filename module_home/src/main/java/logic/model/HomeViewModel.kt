package logic.model

import androidx.lifecycle.MutableLiveData
import com.example.commonlibary.base.BaseViewModel
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
    public fun requestBanner(): MutableLiveData<Response<Banner>> {
        return mRepository.requestBanner()
    }
}