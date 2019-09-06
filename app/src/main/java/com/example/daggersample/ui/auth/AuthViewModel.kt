package com.example.daggersample.ui.auth

import android.util.Log.d
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {

    init {
        d("authviewmodel", "viewmodel is working...")
    }
}