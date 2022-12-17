package com.movieapp.commonUtils

//Resource class use for fetching the API response
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>(data: T? = null) : Resource<T>(data)

    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    class Success<T>(data: T) : Resource<T>(data)

}