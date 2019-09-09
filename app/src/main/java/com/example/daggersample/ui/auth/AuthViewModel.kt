package com.example.daggersample.ui.auth

import aioria.com.br.kotlinbaseapp.networking.EndpointsInterface
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggersample.networking.User
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(var endpointsInterface: EndpointsInterface) : ViewModel() {
    var authUser = MediatorLiveData<User>()


    init {

    }


    fun observeUser() : LiveData<User> {
        return authUser
    }

    fun authenticateWithId(userId:Int){
        var source : LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            endpointsInterface.getUser(userId)
                .subscribeOn(Schedulers.io())
        )

        authUser.addSource(source, {
            authUser.value = it
            authUser.removeSource(source)
        })
    }

}