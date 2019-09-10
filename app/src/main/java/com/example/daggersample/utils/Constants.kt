package com.example.daggersample.utils

public class Constants(){
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com"
        val CACHE_SIZE = 5 * 1024 * 1024
        val MAX_AGE = 30
        val MAX_STALE = 7*24*60*60
        val TIMEOUT: Long = 10
    }

}