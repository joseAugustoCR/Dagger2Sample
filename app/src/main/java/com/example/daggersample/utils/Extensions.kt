package com.example.daggersample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.hasNetwork(): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}
