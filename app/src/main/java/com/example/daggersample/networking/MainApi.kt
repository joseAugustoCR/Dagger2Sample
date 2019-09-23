package aioria.com.br.kotlinbaseapp.networking
import com.example.daggersample.networking.Post
import com.example.daggersample.networking.User
import io.reactivex.Flowable
import retrofit2.Call
import java.util.*

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*
import kotlin.collections.ArrayList


interface MainApi{


    @GET("posts")
    fun getPostsFromUser(@Query("userId") id:Int): Flowable<List<Post>>


}