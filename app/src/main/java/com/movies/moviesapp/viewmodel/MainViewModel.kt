package com.movies.moviesapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.movies.domain.usecase.MoviesUseCase
import com.movies.domain.utils.Resource
import com.movies.moviesapp.uiState.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(val movieUseCase: MoviesUseCase,
                                        stateHandle: SavedStateHandle
): ViewModel()
{
    private val _state = mutableStateOf(MovieState())

    var moviesJob: Job? = null

    val genreId = stateHandle.get<Int>("genre_id")
    val genreName = stateHandle.get<String>("genre_name")

    val movies: State<MovieState>
        get() = _state


     fun getMoviesA(language: String, genresId: String)
     {
        moviesJob = movieUseCase(catId = genresId, lang = language).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _state.value = MovieState(
                        movies = result.data)
                }
                is Resource.Error -> {
                    Log.d(TAG, "diaa error: ${result.message}")
                    _state.value = MovieState(
                        error = result.message ?: "Error happened"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieState(isLoading = true)
                }
            }
        }.distinctUntilChanged().launchIn(viewModelScope)

    }

    override fun onCleared() {
        super.onCleared()
        moviesJob?.cancel()

    }
}