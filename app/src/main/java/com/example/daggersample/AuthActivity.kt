package com.example.daggersample

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import java.lang.annotation.Inherited
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {

//    @Named("doSomehing")
    @Inject
    lateinit var drawableLogo:Drawable

    @Inject
    lateinit var requestManager:RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setLogo()


    }

    fun setLogo(){
        requestManager.load(drawableLogo).into(login_logo)
    }

}
