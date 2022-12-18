package com.movieapp.presentation.state

import com.movieapp.commonUtils.Constant.FALSE
import com.movieapp.domain.model.MovieList

data class MovieListState(
    val data: List<MovieList>? = null,
    val error: String = "EMPTY_STRING",
    val isLoading: Boolean = FALSE
)