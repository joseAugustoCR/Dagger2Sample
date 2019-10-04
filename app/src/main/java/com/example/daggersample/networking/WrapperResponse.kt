package com.example.daggersample.networking

class WrapperResponse<T>(val status: Status, val data: T?, val message: String?, var statusCode:Int?=0) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T?, statusCode: Int?): WrapperResponse<T> {
            return WrapperResponse(Status.SUCCESS, data, null, statusCode)
        }

        fun <T> error(msg: String, data: T?, statusCode: Int?): WrapperResponse<T> {
            return WrapperResponse(Status.ERROR, data, msg, statusCode)
        }

        fun <T> loading(data: T?): WrapperResponse<T> {
            return WrapperResponse(Status.LOADING, data, null)
        }
    }
}