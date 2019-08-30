package com.example.daggersample

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity

public abstract class BaseActivity<T : ViewModel> : DaggerAppCompatActivity() {

    lateinit var viewModel : T



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}