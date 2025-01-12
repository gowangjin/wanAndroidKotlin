package com.example.module_search.ui

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlibary.base.BaseActivity
import com.example.commonlibary.base.SimpleTextWatcher
import com.example.commonlibary.constant.Constant
import com.example.commonlibary.listener.IAdapterItemOnClickListener
import com.example.commonlibary.util.LogUtil
import com.example.module_search.BR
import com.example.module_search.R
import com.example.module_search.adapter.SearchResultAdapter
import com.example.module_search.databinding.ActivitySearchBinding
import com.example.module_search.logic.model.SearchModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding,SearchModel>(),
    IAdapterItemOnClickListener {
    companion object{
        private const val TAG = "SearchActivity"
    }
    @Inject
    lateinit var mSearchResultAdapter:SearchResultAdapter
    private val mEditTextWatcher = object : SimpleTextWatcher() {
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
        initView()
        initHotKeyWords()
        initObserve()
        mBinding.searchEditText.addTextChangedListener(mEditTextWatcher)
    }

    override fun providerVMClass(): Class<SearchModel> {
        return SearchModel::class.java
    }

    private fun initView(){
        mBinding.searchResultList.let {
            it.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            it.adapter = mSearchResultAdapter
            mSearchResultAdapter.addItemClickListener(this)
        }
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

    private fun initObserve(){
        mViewModel.mSearchResultLiveData.observe(this) {
            if(it != null && it.size > 0){
                LogUtil.d(TAG,"initObserve $it")
                mBinding.smartRefreshLayout.visibility = View.VISIBLE
                mSearchResultAdapter.setData(it)
                mBinding.searchHotKeywordLayout.visibility = View.GONE
            } else {
                mBinding.ivSearchResultNull.visibility = View.VISIBLE
                mBinding.searchHotKeywordLayout.visibility = View.GONE
                mBinding.smartRefreshLayout.visibility = View.GONE
            }
        }
    }

    override fun onItemClick(position: Int) {
        val dataList = mViewModel.mSearchResultLiveData.value
        if(dataList != null && position < dataList.size){
            val data = dataList[position]
            ARouter.getInstance()
                .build(Constant.PATH_WEB)
                .withString(Constant.WEB_LINK,data.link)
                .navigation()
        }
    }
}