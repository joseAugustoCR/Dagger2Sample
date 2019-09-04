package com.example.daggersample.viewmodels

import android.app.Application
import android.util.Log.d

import androidx.lifecycle.AndroidViewModel

class MainViewModel(var app:Application):AndroidViewModel(app) {


    fun doSomething(){
        d("mainviewmodel", "doing something...")
    }
}