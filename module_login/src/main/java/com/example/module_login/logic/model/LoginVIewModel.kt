package com.example.module_login.logic.model

import androidx.lifecycle.MutableLiveData
import com.example.commonlibary.base.BaseViewModel
import com.example.commonlibary.gson.LoginBean
import com.example.commonlibary.gson.Response
import com.example.module_login.logic.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var mRepository: LoginRepository
    public suspend fun login(userName:String,password:String):MutableLiveData<Response<LoginBean>>{
        return mRepository.login(userName,password)
    }
}