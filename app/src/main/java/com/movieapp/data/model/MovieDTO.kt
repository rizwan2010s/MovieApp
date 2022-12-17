package com.movieapp.data.model

data class MovieDTO(
    val page: Int?,
    val results: List<ResultDTO>?,
    val total_pages: Int?,
    val total_results: Int?
)