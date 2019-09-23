package com.example.daggersample.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggersample.SessionManager
import com.example.daggersample.networking.User
import com.example.daggersample.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var sessionManager: SessionManager): ViewModel() {

    fun getAuthUser() : LiveData<AuthResource<User>>{
        return sessionManager.getAuthUser()
    }



}