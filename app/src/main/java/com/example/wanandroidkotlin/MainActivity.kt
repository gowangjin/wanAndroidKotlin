package com.example.wanandroidkotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import com.example.commonlibary.base.BaseActivity
import com.example.commonlibary.base.BaseFragment
import com.example.module_home.ui.HomeFragment
import com.example.wanandroidkotlin.adapter.MainViewPagerAdapter
import com.example.wanandroidkotlin.databinding.MainActivityBinding
import com.example.wanandroidkotlin.logic.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding,MainActivityViewModel>() {
    private lateinit var mMainViewPagerAdapter: MainViewPagerAdapter
    override fun getLayoutId(): Int {
        return R.layout.main_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun providerVMClass(): Class<MainActivityViewModel> {
        return MainActivityViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.add(HomeFragment())
        mMainViewPagerAdapter = MainViewPagerAdapter(this,fragmentList)
        mBinding.viewPager.adapter = mMainViewPagerAdapter
        mBinding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> mBinding.viewPager.currentItem = 0
//                R.id.project -> mBinding.viewPager.currentItem = 1
//                R.id.navigation -> mBinding.viewPager.currentItem = 2
//                R.id.favorite -> mBinding.viewPager.currentItem = 3
            }
            true
        }
    }
}