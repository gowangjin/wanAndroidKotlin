package com.example.module_favorite.ui

import com.example.commonlibary.base.BaseFragment
import com.example.module_favorite.R
import com.example.module_favorite.databinding.FragmentFavoriteBinding
import com.example.module_favorite.logic.model.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment @Inject constructor() : BaseFragment<FragmentFavoriteBinding,FavoriteViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_favorite
    }

    override fun providerVMClass(): Class<FavoriteViewModel> {
        return FavoriteViewModel::class.java
    }
}