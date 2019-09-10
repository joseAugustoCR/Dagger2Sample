package com.example.daggersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.daggersample.networking.User
import com.example.daggersample.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private var cachedUser:MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(source : LiveData<AuthResource<User>>){
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source, Observer {
            cachedUser.value = it
            cachedUser.removeSource(source)
        })
    }

    fun logout(){
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<User>>{
        return cachedUser
    }


}