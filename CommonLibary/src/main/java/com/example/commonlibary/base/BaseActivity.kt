package com.example.commonlibary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.commonlibary.util.LogUtil

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    companion object{
        private const val TAG = "BaseActivity"
    }

    private lateinit var mBinding : T

    abstract fun getLayoutId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,getLayoutId())
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG,"onDestroy")
        mBinding.unbind()
    }
}