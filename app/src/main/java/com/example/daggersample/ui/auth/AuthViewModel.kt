package com.example.daggersample.ui.auth

import aioria.com.br.kotlinbaseapp.networking.EndpointsInterface
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggersample.SessionManager
import com.example.daggersample.networking.User
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(var endpointsInterface: EndpointsInterface, var sessionManager: SessionManager) : ViewModel() {


    init {

    }


    fun observeAuthState() : LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

    fun queryUserId(id:Int) : LiveData<AuthResource<User>>{
        return LiveDataReactiveStreams.fromPublisher(
            endpointsInterface.getUser(id)
                .onErrorReturn(Function<Throwable, User> {
                    val errorUser = User()
                    errorUser.id = -1
                    errorUser
                })

                // wrap User object in AuthResource
                .map(Function<User, AuthResource<User>> { user ->
                    if (user.id == -1) {
                        AuthResource.error("Could not authenticate", null)
                    } else AuthResource.authenticated(user)
                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun authenticateWithId(userId:Int){
        d("auth", "attempting to login")

        sessionManager.authenticateWithId(queryUserId(userId))
    }

}