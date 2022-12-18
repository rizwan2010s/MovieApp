package com.movieapp.commonUtils
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    //Interface for checking the Internet Status
    fun observe(): Flow<Status>

    enum class Status{
        AVAILABLE,UNAVAILABLE,LOST,LOSING
    }
}