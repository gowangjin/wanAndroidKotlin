package com.example.module_project.adapter

import com.bumptech.glide.Glide
import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.module_project.R
import com.example.module_project.BR
import com.example.module_project.databinding.ProjectChildItemBinding
import javax.inject.Inject

/**
 * 项目Child
 */
class ProjectChildAdapter @Inject constructor() : BaseRecyclerAdapter<ArticleDetailBean,ProjectChildItemBinding>
    (R.layout.project_child_item,BR.data) {
    override fun onBindViewHolder(data: ArticleDetailBean) {
        super.onBindViewHolder(data)
        Glide.with(mDataBinding.ivArticle)
            .load(data.envelopePic)
            .into(mDataBinding.ivArticle)
    }

}