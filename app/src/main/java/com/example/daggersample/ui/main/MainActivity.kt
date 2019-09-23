package com.example.daggersample.ui.main

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
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

            }
            R.id.posts ->{

            }
        }
        p0.setChecked(true)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testFragment()
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

    fun testFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.main_container, PostsFragment()).commit()
    }
}
