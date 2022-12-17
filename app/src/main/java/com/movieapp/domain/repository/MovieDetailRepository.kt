package com.movieapp.domain.repository

import com.movieapp.data.model.MovieDetailDTO

interface MovieDetailRepository {
    suspend fun getMovieDetails(id: Int?): MovieDetailDTO
}