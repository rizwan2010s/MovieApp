package com.movieapp.domain.repository

import com.movieapp.data.model.MovieDTO

interface MovieRepository {
    suspend fun getMovieList():MovieDTO
}