package com.example.module_navi.logic.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commonlibary.base.BaseViewModel
import com.example.commonlibary.gson.NaviTreeBean
import com.example.commonlibary.util.LogUtil
import com.example.module_navi.logic.repository.NaviRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NaviViewModel @Inject constructor() : BaseViewModel() {
    companion object{
        private const val TAG = "NaviViewModel"
    }
    @Inject
    lateinit var mNaviRepository:NaviRepository
    val mNaviTreeMutableList = MutableLiveData<MutableList<NaviTreeBean>>()
    fun getNaviTree(){
        viewModelScope.launch {
            val articleDetailBeanList = mNaviRepository.getNaviTree().data
            articleDetailBeanList.let {
                LogUtil.d(TAG,"data size ${it.toString()}")
                mNaviTreeMutableList.value = it
            }
        }
    }
}