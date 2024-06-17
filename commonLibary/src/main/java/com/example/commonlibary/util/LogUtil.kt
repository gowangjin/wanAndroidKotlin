package com.example.commonlibary.util

import android.util.Log

object LogUtil {
    @JvmStatic
    fun d(tag : String,msg : String){
        Log.d(tag, "${Thread.currentThread()},$msg")
    }

    @JvmStatic
    fun e(tag : String,msg : String){
        Log.e(tag, msg)
    }

}
