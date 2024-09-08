package com.example.module_project.adapter

import com.example.commonlibary.base.BaseRecyclerAdapter
import com.example.commonlibary.gson.ArticleDetailBean
import com.example.module_project.R
import com.example.module_project.BR
import com.example.module_project.databinding.ProjectChildItemBinding

class ProjectChildAdapter : BaseRecyclerAdapter<ArticleDetailBean,ProjectChildItemBinding>
    (R.layout.project_child_item,BR.data) {
}