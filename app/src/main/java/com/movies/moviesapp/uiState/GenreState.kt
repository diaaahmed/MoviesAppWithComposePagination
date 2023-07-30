package com.movies.moviesapp.uiState

import com.movies.domain.entity.Genre


data class GenreState (
    val isLoading: Boolean = false,
    val genre : List<Genre>  = emptyList(),
    val error: String = ""
)
