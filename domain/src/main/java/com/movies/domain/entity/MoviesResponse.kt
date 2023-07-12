package com.movies.domain.entity

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    @SerializedName("results")
    val movieItems: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
)