package com.example.daggersample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.daggersample.R
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    var viewModel:AuthViewModel?=null

    @Blablaa(name = "") class c

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


    }

    fun setLogo(){
        requestManager.load(drawableLogo).into(login_logo)
    }

}

annotation class Blablaa(val name: String)

annotation class Blabla(val name: String)
