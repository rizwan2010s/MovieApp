package com.movieapp.commonUtils
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status{
        Available,UnAvailable,Lost,Losing
    }
}