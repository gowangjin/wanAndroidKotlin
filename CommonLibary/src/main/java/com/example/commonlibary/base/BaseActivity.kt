package com.example.commonlibary.base

import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.commonlibary.util.LogUtil
import kotlin.math.log
import kotlin.math.sign

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    companion object{
        private const val TAG = "BaseActivity"
    }

    private lateinit var mBinding : T

    abstract fun getLayoutId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomDensity()
        mBinding = DataBindingUtil.setContentView(this,getLayoutId())
    }

    private var sNoscompatDensity = 0F
    private var sNoncompatScaledDensity = 0F
    private fun setCustomDensity(){
        val appDisplayMetrics : DisplayMetrics = application.resources.displayMetrics
        if(sNoscompatDensity == 0F){
            sNoscompatDensity = appDisplayMetrics.density
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity
            registerComponentCallbacks(object : ComponentCallbacks{
                override fun onConfigurationChanged(newConfig: Configuration) {
                    if(newConfig.fontScale > 0){
                        sNoncompatScaledDensity = appDisplayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {

                }
            })
        }
        val targetDensity  = appDisplayMetrics.widthPixels / 360
        val targetScaleDensity = targetDensity * (sNoncompatScaledDensity / sNoscompatDensity)
        val targetDensityDip = 160 * targetDensity
        LogUtil.d(TAG, "targetDensity: $targetDensity targetScaleDensity: $targetScaleDensity targetDensityDip: $targetDensityDip")
        appDisplayMetrics.density = targetDensity.toFloat()
        appDisplayMetrics.scaledDensity = targetScaleDensity
        appDisplayMetrics.densityDpi = targetDensityDip
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG,"onDestroy")
        mBinding.unbind()
    }
}