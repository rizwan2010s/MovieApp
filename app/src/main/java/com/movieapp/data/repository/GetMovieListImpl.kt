package com.movieapp.data.repository

import com.movieapp.commonUtils.Constant.APIKEY
import com.movieapp.data.model.MovieDTO
import com.movieapp.data.remote.MovieAPI
import com.movieapp.domain.repository.MovieRepository

class GetMovieListImpl(private val movieAPI: MovieAPI) :  MovieRepository{
    override suspend fun getMovieList(): MovieDTO {
        return movieAPI.getMovieList(APIKEY)
    }
}