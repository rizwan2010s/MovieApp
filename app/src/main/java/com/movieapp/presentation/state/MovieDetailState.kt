package com.movieapp.presentation.state

import com.movieapp.commonUtils.Constant.EMPTY_STRING
import com.movieapp.commonUtils.Constant.FALSE
import com.movieapp.domain.model.MovieDetail

data class MovieDetailState(
    val data:MovieDetail? = null,
    val error: String = EMPTY_STRING,
    val isLoading: Boolean = FALSE
) {
}