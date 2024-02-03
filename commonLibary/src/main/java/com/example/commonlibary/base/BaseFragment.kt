package com.example.commonlibary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.commonlibary.util.LogUtil

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    private val TAG = this.javaClass.simpleName
    lateinit var mBinding : T
    abstract fun getLayoutId() : Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtil.d(TAG, "onCreateView: ")
        mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container
        ,false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG,"onDestroy")
        mBinding.unbind()
    }
}