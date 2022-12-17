package com.movieapp.data.repository

import com.movieapp.commonUtils.Constant.APIKEY
import com.movieapp.data.model.MovieDetailDTO
import com.movieapp.data.remote.MovieAPI
import com.movieapp.domain.repository.MovieDetailRepository

class GetMovieDetailImpl(private val movieAPI: MovieAPI) : MovieDetailRepository {
    override suspend fun getMovieDetails(id: Int?): MovieDetailDTO {
        return movieAPI.getMovieDetail(id,APIKEY)
    }
}