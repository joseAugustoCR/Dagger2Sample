package com.example.daggersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {

//    @Named("doSomehing")
    @Inject
    lateinit var myString:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        d("auth", "onCreate: $myString")
    }
}
