package com.example.wanandroidkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.commonlibary.base.BaseActivity
import com.example.wanandroidkotlin.databinding.MainActivityBinding
import com.example.wanandroidkotlin.ui.theme.WanAndroidKotlinTheme

class MainActivity : BaseActivity<MainActivityBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.main_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}