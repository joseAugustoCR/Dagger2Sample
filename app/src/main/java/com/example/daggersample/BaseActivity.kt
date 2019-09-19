package com.example.daggersample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.daggersample.ui.auth.AuthActivity
import com.example.daggersample.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

public abstract class BaseActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var sessionManager:SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()

    }

    fun subscribeObservers(){
        sessionManager.getAuthUser().observe(this, Observer {
            it.let {
                when(it.status){
                    AuthResource.AuthStatus.LOADING ->{

                    }
                    AuthResource.AuthStatus.AUTHENTICATED ->{
                        Log.d("auth", "success")

                    }
                    AuthResource.AuthStatus.ERROR ->{
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED ->{
                        navLoginScreen()


                    }
                }
            }
        })
    }


    fun navLoginScreen(){
        startActivity<AuthActivity>()
        finish()
    }


}