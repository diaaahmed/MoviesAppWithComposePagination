package com.movies.moviesapp.uiState

import androidx.paging.Pager
import com.movies.domain.entity.MovieItem


data class MovieState (
    val isLoading: Boolean = false,
    val movies : Pager<Int, MovieItem>? = null ,
    val error: String = ""
)
