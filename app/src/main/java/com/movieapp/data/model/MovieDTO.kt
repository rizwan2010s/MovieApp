package com.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ResultDTO>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)