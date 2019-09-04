package com.example.daggersample

import android.os.Bundle
import com.example.daggersample.viewmodels.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
