package com.movieapp.data.model

import com.movieapp.domain.model.MovieList

data class ResultDTO(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun ResultDTO.toDomainMovieList() : MovieList
{
    return MovieList(
        id = this.id,
        name = this.original_title,
        imagePath = this.poster_path
    );
}
