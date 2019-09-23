package aioria.com.br.kotlinbaseapp.networking
import com.example.daggersample.networking.User
import io.reactivex.Flowable
import retrofit2.Call
import java.util.*

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*
import kotlin.collections.ArrayList


interface AuthApi{

    @GET("users/{id}")
    fun getUser(@Path("id") id:Int): Flowable<User>




}