package com.example.module_search.logic.model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commonlibary.base.BaseViewModel
import com.example.commonlibary.constant.ErrorCode
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.commonlibary.gson.SearchHotKeyWord
import com.example.commonlibary.util.LogUtil
import com.example.module_search.logic.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchModel @Inject constructor():BaseViewModel() {
    @Inject
    lateinit var mRepository : SearchRepository
    // 定义输入框内容输入的可观察字段
    val mInputContent = ObservableField<String>()
    val mHotKeyWordMutableList = MutableLiveData<MutableList<SearchHotKeyWord>>()
    val mSearchResultLiveData = MutableLiveData<MutableList<ArticleDetailBean>?>()
    companion object{
        private const val TAG = "SearchModel"
    }
    /**
     *获取搜索热词
     */
    fun getHotKeyWords(){
        viewModelScope.launch {
            val hotKeyWordResponse = mRepository.getSearchHotKeyWords()
            hotKeyWordResponse.let {
                LogUtil.d(TAG,"Response ErrorCode ${hotKeyWordResponse.errorCode}")
                val keyWordList = it.data
                if(it.errorCode == ErrorCode.OK && keyWordList.isNotEmpty()){
                    mHotKeyWordMutableList.value = keyWordList
                }
            }
        }
    }

    /**
     * 发起搜索
     */
    fun startKeyWordSearch(){
        viewModelScope.launch {
            val keyWord = mInputContent.get()
            if(!keyWord.isNullOrEmpty()){
                LogUtil.d(TAG,"keyWord $keyWord")
                val searchResult = mRepository.getSearchResult(keyWord,0)
                LogUtil.d(TAG,"startKeyWordSearch $searchResult")
                mSearchResultLiveData.value = searchResult.data.articleList
            } else {
                LogUtil.d(TAG,"keyWord is null")
            }
        }
    }
}