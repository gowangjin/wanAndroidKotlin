package logic.model

import com.example.commonlibary.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import logic.repository.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var mRepository : HomeRepository
    public fun requestBanner(){
        mRepository.requestBanner()
    }
}