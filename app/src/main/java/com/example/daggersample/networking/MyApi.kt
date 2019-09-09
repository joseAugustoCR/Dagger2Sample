package aioria.com.br.kotlinbaseapp.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log.d
import com.example.daggersample.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class MyApi(context: Context)
{
    var retrofit:Retrofit;
    var endpointsInterface:EndpointsInterface
    var RETROFIT_LOG_LEVEL: HttpLoggingInterceptor.Level ?= null
    val baseURL = "https://livecreator.dev.aioria.com.br/api/v1/app/"


    init
    {
        retrofit = buildRestAdapter(baseURL, context)
        endpointsInterface = retrofit.create(EndpointsInterface::class.java)
    }

    companion object
    {
        @Volatile private var INSTANCE: MyApi? = null
        fun getInstance(context: Context): MyApi =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: MyApi(context).also { INSTANCE = it }

                }
    }


    inner class UnauthorizedInterceptor : Interceptor {
        var okhttpClient:OkHttpClient? = null
        var requestBody: RequestBody? = null
//        var tokenRequest: Request
        var gson: Gson

        init {
            okhttpClient = OkHttpClient.Builder().build()
            gson = Gson()
            val mediaType = MediaType.parse("application/json; charset=utf-8")
//            requestBody = RequestBody.create(mediaType, gson.toJson(TokenRequest()))

//            tokenRequest = Request.Builder()
//                    .url(baseURL + "token")
//                    .post(requestBody!!)
//                    .build()
        }

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {


            var originalResponse = chain.proceed(chain.request())
            d("status " + originalResponse.code(),"")
            d("url " + chain.request().url().toString(),"")

//            if(chain.request().url().toString().contains("/token") == false) {

//                if (originalResponse.code() == 401) {
//                    var tokenResponse = okhttpClient?.newCall(tokenRequest)?.execute()
//                    if (tokenResponse?.isSuccessful == true) {
//                        try {
//                            val jsonObject = JSONObject(tokenResponse.body()?.string())
//                            if (jsonObject.has("data")) {
//                                var tokenData = gson.fromJson(jsonObject.getJSONObject("data").toString(), TokenResponse::class.java)
//                                UserPref.token = tokenData?.token
//                                var newRequest = chain.request().newBuilder().header("X-Token", UserPref.token.toString()).build()
//                                var newResponse = chain.proceed(newRequest)
//                                d { "new " + tokenData.toString() }
//                                return newResponse
//                            }
//                        } catch (e: Exception) {
//                        }
//                    }
//                }
//            }
            return  originalResponse
        }

    }



    protected fun buildRestAdapter(host: String, ctx:Context): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        RETROFIT_LOG_LEVEL = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(RETROFIT_LOG_LEVEL)

//        CustomApplication.g
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache =  Cache(ctx.cacheDir, cacheSize)


        val okHttpClient = OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor{ chain ->

                    // Get the request from the chain.
                    var request = chain.request()

                    /*
                    *  Leveraging the advantage of using Kotlin,
                    *  we initialize the request and change its header depending on whether
                    *  the device is connected to Internet or not.
                    */
                    request = if (hasNetwork(ctx)!!)
                    /*
                    *  If there is Internet, get the cache that was stored 5 seconds ago.
                    *  If the cache is older than 5 seconds, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-age' attribute is responsible for this behavior.
                    */
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 6).build()
                    else
                    /*
                    *  If there is no Internet, get the cache that was stored 7 days ago.
                    *  If the cache is older than 7 days, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-stale' attribute is responsible for this behavior.
                    *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                    */
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 14).build()
                    // End of if-else statement

                    // Add the modified request to the chain.
                    chain.proceed(request)
                }
                .addInterceptor(UnauthorizedInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()


    }


    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }



}

























































