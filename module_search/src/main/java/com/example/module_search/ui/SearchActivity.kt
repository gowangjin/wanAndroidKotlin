package com.example.module_search.ui

import android.os.Bundle
import android.text.Editable
import android.view.View
import com.example.commonlibary.base.BaseActivity
import com.example.commonlibary.base.SimpleTextWatcher
import com.example.commonlibary.util.LogUtil
import com.example.module_search.BR
import com.example.module_search.R
import com.example.module_search.databinding.ActivitySearchBinding
import com.example.module_search.logic.model.SearchModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding,SearchModel>() {
    companion object{
        private const val TAG = "SearchActivity"
    }
    val mEditTextWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            LogUtil.d(TAG,"afterTextChanged $s")
            val length = s?.length ?: 0
            mBinding.ivClearSearch.visibility = if(length > 0)
                View.VISIBLE else View.GONE
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.setVariable(BR.SearchActivity,this)
        mBinding.setVariable(BR.SearchModel,mViewModel)
    }

    override fun onStart() {
        super.onStart()
        initHotKeyWords()
        mBinding.searchEditText.addTextChangedListener(mEditTextWatcher)
    }

    override fun providerVMClass(): Class<SearchModel> {
        return SearchModel::class.java
    }

    private fun initHotKeyWords(){
        mViewModel.mHotKeyWordMutableList.observe(this) {
            for(i in it.indices){
                LogUtil.d(TAG,"initHotKeyWords ${it[i]}")
                val childView = SearchFlexBoxChildView(this)
                childView.setData(it[i])
                mBinding.searchFlexBoxLayout.addView(childView)
            }
        }
        mViewModel.getHotKeyWords()
    }

    fun clearEditTextInput(){
        mBinding.searchEditText.text = null
    }
}