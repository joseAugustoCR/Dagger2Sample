package com.example.daggersample.ui.main

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.daggersample.BaseActivity
import com.example.daggersample.R
import com.example.daggersample.ui.main.posts.PostsFragment
import com.example.daggersample.ui.main.profile.ProfileFragment
import com.example.daggersample.viewmodels.MainViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.profile ->{
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileScreen)
            }
            R.id.posts ->{
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsScreen)

            }
        }
        p0.setChecked(true)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.logout){
            sessionManager.logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun init(){
        var navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)

    }
}
