package com.example.daggersample.ui.main.posts

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.daggersample.R
import com.example.daggersample.networking.Post
import com.example.daggersample.ui.main.Resource
import com.example.daggersample.utils.VerticalSpaceItemDecoration
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.posts_fragment.*
import javax.inject.Inject

class PostsFragment : DaggerFragment() {


    private lateinit var viewModel: PostsViewModel
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject lateinit var adapter:PostAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel::class.java)
        d("posts", "created")
        initRecycler()
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(this, Observer {
            d("posts", "changed")
            when(it.status){
                Resource.Status.SUCCESS ->{
                    adapter.updateList(it.data)
                }
                Resource.Status.ERROR ->{
                    d("posts", "error")
                }
                Resource.Status.LOADING -> {
                    d("posts", "loading")

                }
            }
        })
    }

    fun initRecycler(){
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.addItemDecoration(VerticalSpaceItemDecoration(15))
        recycler_view.adapter = adapter

    }

}
