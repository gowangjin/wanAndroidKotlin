package com.example.module_login.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibary.base.BaseActivity
import com.example.commonlibary.constant.Constant
import com.example.module_login.R
import com.example.module_login.databinding.LoginActivityBinding
import com.example.module_login.logic.model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Constant.PATH_LOGIN)
class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>(){
    override fun getLayoutId(): Int {
        return R.layout.login_activity
    }

    override fun providerVMClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }
}