package com.example.daggersample.ui.main

import com.example.daggersample.utils.getErrorMsg
import com.example.daggersample.utils.getStatusCode


class Resource<T>(val status: Status, val data: T?=null, val e:Throwable?=null,var msg:String?="", var statusCode:Int? = 0) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(e: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, e , e.getErrorMsg(), statusCode = e.getStatusCode())
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}