package com.movies.moviesapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.movies.domain.usecase.MoviesUseCase
import com.movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(val movieUseCase: MoviesUseCase): ViewModel()
{
    private val _state = mutableStateOf(MovieState())

    val movies: State<MovieState>
        get() = _state

    fun getMoviesA(language: String, genresId: String) {

        movieUseCase(catId = genresId, lang = language).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieState(
                        movies = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = MovieState(
                        error = result.message ?: "Error happened"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)

    }
}