package com.example.daggersample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.daggersample.ui.main.Resource
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

fun Context.hasNetwork(): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun Throwable.getErrorMsg() : String{
    var statusCode: Int? = 0
    var errorMsg:String? = ""

    if (this is HttpException) {
        val responseBody: ResponseBody =
            (this as HttpException).response().errorBody() as ResponseBody
        try {
            val jsonObject = JSONObject(responseBody.string())
            errorMsg = jsonObject.getString("message")
        } catch (e: Exception) {
        }
    }
    statusCode = (this as HttpException).response()?.code()
    if(errorMsg.isNullOrEmpty() == false){
        return errorMsg.toString()
    }
    if(statusCode == 406 || statusCode == 409){
        return "User already exist"
    }
    return "Something went wrong"
}

fun Throwable.getStatusCode() : Int{
    var statusCode: Int? = 0
    var errorMsg:String? = ""

    if (this is HttpException) {
        val responseBody: ResponseBody =
            (this as HttpException).response().errorBody() as ResponseBody
        try {
            val jsonObject = JSONObject(responseBody.string())
            errorMsg = jsonObject.getString("message")
        } catch (e: Exception) {
        }
    }
    statusCode = (this as HttpException).response()?.code()
    return if(statusCode != null) statusCode else 0
}

