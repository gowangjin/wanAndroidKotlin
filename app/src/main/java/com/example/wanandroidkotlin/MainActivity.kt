package com.example.wanandroidkotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.commonlibary.base.BaseActivity
import com.example.commonlibary.base.BaseFragment
import com.example.commonlibary.util.LogUtil
import com.example.module_home.ui.HomeFragment
import com.example.module_project.ui.ProjectFragment
import com.example.wanandroidkotlin.adapter.MainViewPagerAdapter
import com.example.wanandroidkotlin.databinding.MainActivityBinding
import com.example.wanandroidkotlin.logic.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding,MainActivityViewModel>() {
    companion object{
        private const val TAG = "MainActivity"
    }
    private lateinit var mMainViewPagerAdapter: MainViewPagerAdapter
    @Inject
    lateinit var mHomeFragment: HomeFragment
    @Inject
    lateinit var mProjectFragment: ProjectFragment
    override fun getLayoutId(): Int {
        return R.layout.main_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(TAG, "onCreate: ")
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.add(mHomeFragment)
        fragmentList.add(mProjectFragment)
        mMainViewPagerAdapter = MainViewPagerAdapter(this,fragmentList)
        mBinding.viewPager.adapter = mMainViewPagerAdapter
        mBinding.bottomNavi.setOnItemSelectedListener {
            LogUtil.d(TAG,"setOnItemSelectedListener ${it.itemId}")
            when(it.itemId){
                R.id.home -> mBinding.viewPager.currentItem = 0
                R.id.project -> mBinding.viewPager.currentItem = 1
//                R.id.navigation -> mBinding.viewPager.currentItem = 2
//                R.id.favorite -> mBinding.viewPager.currentItem = 3
            }
            true
        }
    }

    override fun providerVMClass(): Class<MainActivityViewModel> {
        return MainActivityViewModel::class.java
    }
}