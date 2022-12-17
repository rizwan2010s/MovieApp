package com.movieapp.data.remote

import com.movieapp.commonUtils.Constant.API_KEY
import com.movieapp.commonUtils.Constant.MOVIE_ID
import com.movieapp.data.model.MovieDTO
import com.movieapp.data.model.MovieDetailDTO
import retrofit2.http.*

private const val GET_MOVIE_LIST = "discover/movie"
private const val GET_MOVIE_DETAIL = "movie/{movieId}"

interface MovieAPI {

    @GET(GET_MOVIE_LIST)
    suspend fun getMovieList(@Query(API_KEY) api_key:String?): MovieDTO

    @GET(GET_MOVIE_DETAIL)
    suspend fun getMovieDetail(@Path(MOVIE_ID) movieId:Int?,@Query(API_KEY) api_key:String?): MovieDetailDTO
}