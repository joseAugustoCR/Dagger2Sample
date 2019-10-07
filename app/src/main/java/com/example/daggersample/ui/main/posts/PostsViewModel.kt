package com.example.daggersample.ui.main.posts

import aioria.com.br.kotlinbaseapp.networking.MainApi
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.daggersample.SessionManager
import com.example.daggersample.networking.ErrorResponse
import com.example.daggersample.networking.Post
import com.example.daggersample.networking.User
import com.example.daggersample.ui.auth.AuthResource
import com.example.daggersample.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class PostsViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi) : ViewModel() {

    var posts:MediatorLiveData<Resource<List<Post>>>? = null

    fun observePosts() : LiveData<Resource<List<Post>>>{
        if(posts == null){
            posts = MediatorLiveData()
            posts?.value = Resource.loading(null)

            // network call
            var result = mainApi.getPostsFromUser(sessionManager.getAuthUser().value!!.data!!.id!!)
                .subscribeOn(Schedulers.io())
                .map(Function<List<Post>, Resource<List<Post>> > { p:List<Post> ->
                    Resource.success(p)
                })
                .onErrorReturn {
                    Resource.error(it, null)
                }

            // convert a Rx stream to a LiveData
            val source = LiveDataReactiveStreams.fromPublisher(result)

            //add as a source
            posts?.addSource(source, Observer {
                posts?.value = it
                posts?.removeSource(source)
            })
        }

        return posts!!
    }


}
