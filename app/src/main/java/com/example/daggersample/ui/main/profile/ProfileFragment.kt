package com.example.daggersample.ui.main.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.daggersample.R
import com.example.daggersample.networking.User
import com.example.daggersample.ui.auth.AuthResource
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {

    lateinit var viewModel:ProfileViewModel
    @Inject lateinit var providerFactory:ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        d("profile","create profile" )
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.getAuthUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthUser().observe(viewLifecycleOwner, Observer {
            when(it.status){
                AuthResource.AuthStatus.AUTHENTICATED ->{
                    setData(it.data)

                }
                AuthResource.AuthStatus.ERROR ->{
                    setError(it.message)

                }
            }
        })
    }

    fun setData(data: User?){
        d("profile", "setdata")
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }

    fun setError(message: String?){
        d("profile", "seterror")
        email.text = message
        username.text = "error"
        website.text = "error"
    }


}
