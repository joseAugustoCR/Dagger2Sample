package com.example.daggersample.ui.main.posts

import aioria.com.br.kotlinbaseapp.networking.MainApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.daggersample.SessionManager
import com.example.daggersample.networking.Post
import com.example.daggersample.networking.User
import com.example.daggersample.ui.auth.AuthResource
import com.example.daggersample.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi) : ViewModel() {

    var posts:MediatorLiveData<Resource<List<Post>>>? = null

    fun observePosts() : LiveData<Resource<List<Post>>>{
        if(posts == null){
            posts = MediatorLiveData()
            posts?.value = Resource.loading(null)

            // network call
            var result = mainApi.getPostsFromUser(sessionManager.getAuthUser().value!!.data!!.id!!)
//                .onErrorReturn {
//                    var post = Post(id = -1)
//                    var arrayList = ArrayList<Post>()
//                    arrayList.add(post)
//                    return@onErrorReturn arrayList
//                }
                .map(Function<List<Post>, Resource<List<Post>> > { p:List<Post> ->
                    if(p.size > 0){
                        if(p.get(0).id == -1) {
                            Resource.error("Something went wrong", null)
                        }
                    }
                    Resource.success(p)
                })
                .doOnError {
                    posts?.value = Resource.error("", null)
                }
                .subscribeOn(AndroidSchedulers.mainThread())

            // convert a Rx stream to a LiveData
            val source = LiveDataReactiveStreams.fromPublisher(result)

            //
            posts?.addSource(source, Observer {
                posts?.value = it
                posts?.removeSource(source)
            })

        }

        return posts!!

    }

}
