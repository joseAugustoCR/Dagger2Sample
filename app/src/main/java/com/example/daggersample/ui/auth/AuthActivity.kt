package com.example.daggersample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.daggersample.R
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    var viewModel:AuthViewModel?=null


    @Inject
    lateinit var drawableLogo:Drawable

    @Inject
    lateinit var requestManager:RequestManager

    @Inject
    lateinit var  providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()
        setClickListeners()
        subscribeObservers()

    }


    fun setClickListeners(){
        login_button.setOnClickListener {
            attemptLogin()
        }
    }

    fun setLogo(){
        requestManager.load(drawableLogo).into(login_logo)
    }

    fun attemptLogin(){
        if(user_id_input.text.toString().isEmpty()){
            return
        }
        viewModel?.authenticateWithId(user_id_input.text.toString().toInt())
    }

    fun subscribeObservers(){
        viewModel?.observeAuthState()?.observe(this,
            Observer {
                it.let {
                    when(it.status){
                        AuthResource.AuthStatus.LOADING ->{
                            showProgressBar(true)

                        }
                        AuthResource.AuthStatus.AUTHENTICATED ->{
                            showProgressBar(false)
                            d("auth", "success")

                        }
                        AuthResource.AuthStatus.ERROR ->{
                            showProgressBar(false)
                            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

                        }
                        AuthResource.AuthStatus.NOT_AUTHENTICATED ->{
                            showProgressBar(false)


                        }
                    }
                }
            })


    }


    fun showProgressBar(isVisible:Boolean){
        if(isVisible){
            progress_bar.visibility = View.VISIBLE
        }else{
            progress_bar.visibility = View.GONE
        }
    }

}

