package com.example.commonlibary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.commonlibary.util.LogUtil

abstract class BaseFragment<VD : ViewDataBinding,VM : BaseViewModel> : Fragment() {
    private val TAG = this.javaClass.simpleName
    lateinit var mBinding : VD
    lateinit var mViewModel: VM
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViewModel(){
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this).get(it)
            lifecycle.addObserver(mViewModel)
        }
    }

    open fun providerVMClass():Class<VM>? = null

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG,"onDestroy")
        mBinding.unbind()
        lifecycle.removeObserver(mViewModel)
    }
}