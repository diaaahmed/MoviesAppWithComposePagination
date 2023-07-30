package com.movies.moviesapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.domain.entity.Genre
import com.movies.domain.usecase.CategoryUseCase
import com.movies.domain.utils.Resource
import com.movies.moviesapp.uiState.GenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "CategoryViewModel"


@HiltViewModel
class CategoryViewModel @Inject constructor(val categoryUseCase: CategoryUseCase):ViewModel()
{
    private val _state = mutableStateOf(GenreState())
    private var _selectedGenre = mutableStateOf(Genre())


    val categories: State<GenreState>
        get() = _state

    val selectedGenre: State<Genre>
        get() = _selectedGenre

    fun setSelectedGenre(selectedGenre: Genre){
        _selectedGenre.value = selectedGenre
        _selectedGenre.value = _selectedGenre.value
    }

    init{
        getCategories("en")
    }


     fun getCategories(language:String)
    {
        categoryUseCase(language)
            .onEach {result->
                when(result)
                {
                    is Resource.Loading -> {
                        Log.d(TAG, "diaa getCategories loading: ")
                        _state.value = GenreState(isLoading = true)
                    }

                    is Resource.Success -> {

                        Log.d(TAG, "diaa getCategories success: ")

                        _state.value = GenreState(
                            genre = result.data!!.genres ?: emptyList()
                        )

                    }

                    is Resource.Error -> {
                        _state.value = GenreState(
                            error = result.message?: "Error happened")

                        Log.d(TAG, "diaa getCategories error: ")

                    }
                }
            }.launchIn(viewModelScope)
    }
}