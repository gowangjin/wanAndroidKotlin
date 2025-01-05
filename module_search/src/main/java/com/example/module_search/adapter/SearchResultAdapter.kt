package com.example.module_search.adapter

import androidx.databinding.DataBindingUtil
import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.base.BaseRecyclerViewHolder
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.commonlibary.util.LogUtil
import com.example.commonlibary.util.Utils
import com.example.module_search.R
import com.example.module_search.BR
import com.example.module_search.databinding.SearchResultLayoutBinding
import javax.inject.Inject

class SearchResultAdapter @Inject constructor() :
    BaseRecyclerAdapter<ArticleDetailBean,SearchResultLayoutBinding>(R.layout.search_result_layout,BR.Bean) {
    companion object{
        const val TAG = "SearchResultAdapter"
    }
    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, data: ArticleDetailBean) {
        val dataBinding = DataBindingUtil.getBinding<SearchResultLayoutBinding>(holder.itemView)
        val title = Utils.clearHighlightFormat(data.title)
        LogUtil.d(TAG,"onBindViewHolder title $title")
        dataBinding?.textArticleTitle?.text = title
    }
}