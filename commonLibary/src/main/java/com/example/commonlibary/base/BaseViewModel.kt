package com.example.commonlibary.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.commonlibary.util.LogUtil
/**
 * LifeCycle是一个可以感知宿主生命周期变化的组件.常见的宿主包括Activity/Fragment,Service和Application
 * LifeCycle会持有宿主的生命周期状态信息,当宿主生命周期发生变化时,会通知到监听的宿主的观察者
 * LifeCycle的出现主要是为了解决:系统组件的生命周期与普通组件之间的耦合性
 * 系统组件指：Activity/Fragment、Service 和 Application。
 * 观察者：LifecycleObserver
 */
abstract class BaseViewModel :ViewModel(), DefaultLifecycleObserver {
    private val TAG = "BaseViewModel"
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LogUtil.d(TAG,"onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        LogUtil.d(TAG, "onStart: ")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        LogUtil.d(TAG, "onResume: ")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        LogUtil.d(TAG, "onPause: ")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        LogUtil.d(TAG, "onStop: ")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        LogUtil.d(TAG, "onDestroy: ")
    }
}