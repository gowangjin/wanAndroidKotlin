package com.example.moudle_web.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibary.base.BaseActivity
import com.example.commonlibary.constant.Constant
import com.example.commonlibary.util.LogUtil
import com.example.moudle_web.R
import com.example.moudle_web.databinding.ActivityWebBinding
import com.example.moudle_web.model.WebViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Constant.PATH_WEB)
class WebActivity : BaseActivity<ActivityWebBinding,WebViewModel>() {
    companion object{
        private const val TAG = "WebActivity"
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val articleLink = intent.getStringExtra(Constant.WEB_LINK).toString()
        initWebViewSettings()
        initWebViewClient()
        initWebChromeClient()
        LogUtil.d(TAG,"web link $articleLink")
        mBinding.webContent.loadUrl(articleLink)
    }

    override fun providerVMClass(): Class<WebViewModel> {
        return WebViewModel::class.java
    }

    /**
     * 初始化WebView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings(){
        val webSettings = mBinding.webContent.settings
        webSettings.let {
            it.javaScriptEnabled = true
            //设置自适应屏幕，两者合用
            it.useWideViewPort = true // 将图片调整刀合适WebView的大小
            it.loadWithOverviewMode = true //缩放至屏幕的大小
        }
    }

    /**
     * WebViewClient主要负责帮助WebView处理各种通知和请求事件
     */
    private fun initWebViewClient(){
        mBinding.webContent.webViewClient = object : WebViewClient() {
            //在开始加载网页时回调
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                LogUtil.d(TAG,"onPageStarted")
            }

            //在加载网页完成时回调
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                LogUtil.d(TAG,"onPageFinished")
            }

            //拦截url跳转，在里面添加点击链接跳转或者操作
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                LogUtil.d(TAG,"shouldOverrideUrlLoading")
                return super.shouldOverrideUrlLoading(view, request)
            }

            //加载错误的时候会回调，
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }
        }
    }

    /**
     *WebChromeClient 是 WebView 的一个重要组件，主要用于处理与网页相关的 UI 交互和事件。
     * 它提供了一系列回调方法，允许开发者自定义和处理 JavaScript 弹窗、地理位置权限、文件选择器等功能。
     */
    private fun initWebChromeClient(){
        mBinding.webContent.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                LogUtil.d(TAG,"onProgressChanged")
                super.onProgressChanged(view, newProgress)
            }
        }
    }
}