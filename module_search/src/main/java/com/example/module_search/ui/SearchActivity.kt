package com.example.module_search.ui

import android.os.Bundle
import com.example.commonlibary.base.BaseActivity
import com.example.module_search.BR
import com.example.module_search.R
import com.example.module_search.databinding.ActivitySearchBinding
import com.example.module_search.logic.model.SearchModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding,SearchModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.setVariable(BR.SearchActivity,this)
    }

    override fun providerVMClass(): Class<SearchModel> {
        return SearchModel::class.java
    }
}