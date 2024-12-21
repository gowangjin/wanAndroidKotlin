package com.example.wanandroidkotlin

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibary.base.BaseActivity
import com.example.commonlibary.util.LogUtil
import com.example.module_home.ui.HomeFragment
import com.example.module_navi.ui.NaviFragment
import com.example.module_project.ui.ProjectFragment
import com.example.module_search.ui.SearchActivity
import com.example.wanandroidkotlin.adapter.DrawersLayoutAdapter
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
    @Inject
    lateinit var mNaviFragment: NaviFragment
    @Inject
    lateinit var mDrawersLayoutAdapter: DrawersLayoutAdapter
    override fun getLayoutId(): Int {
        return R.layout.main_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(TAG, "onCreate: ")
        mBinding.setVariable(BR.activity,this)
        window.statusBarColor = ContextCompat.getColor(this, com.example.commonlibary.R.color.color_ff227bfa)
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.add(mHomeFragment)
        fragmentList.add(mProjectFragment)
        fragmentList.add(mNaviFragment)
        mMainViewPagerAdapter = MainViewPagerAdapter(this,fragmentList)
        mBinding.viewPager.isUserInputEnabled = false
        mBinding.viewPager.adapter = mMainViewPagerAdapter
        mBinding.bottomNavi.setOnItemSelectedListener {
            LogUtil.d(TAG,"setOnItemSelectedListener ${it.itemId}")
            when(it.itemId){
                R.id.home -> mBinding.viewPager.currentItem = 0
                R.id.project -> mBinding.viewPager.currentItem = 1
                R.id.navigation -> mBinding.viewPager.currentItem = 2
//                R.id.favorite -> mBinding.viewPager.currentItem = 3
            }
            true
        }
        initDrawerLayout()
        initToolBar()
    }

    override fun providerVMClass(): Class<MainActivityViewModel> {
        return MainActivityViewModel::class.java
    }

    private fun initToolBar(){
        mBinding.toolBar.setNavigationOnClickListener {
            if(!mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                mBinding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun initDrawerLayout(){
        val mData = mViewModel.getDrawersListData(this)
        mBinding.leftDrawer.drawerList.adapter = mDrawersLayoutAdapter
        mBinding.leftDrawer.drawerList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mDrawersLayoutAdapter.setData(mData)
    }

    /**
     *关闭侧滑栏
     */
    fun closeDrawerLayout(){
        mBinding.drawerLayout.closeDrawers()
    }

    /**
     * 搜索
     */
    fun onClickSearch(){
        val intent = Intent(this,SearchActivity::class.java)
        startActivity(intent)
    }
}