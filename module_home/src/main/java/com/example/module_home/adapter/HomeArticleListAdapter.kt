package com.example.module_home.adapter

import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.module_home.BR
import com.example.module_home.R
import com.example.module_home.databinding.HomeAritcleItemLayoutBinding

class HomeArticleListAdapter() :
    BaseRecyclerAdapter<ArticleDetailBean,HomeAritcleItemLayoutBinding>(
        R.layout.home_aritcle_item_layout,
        BR.Bean)
