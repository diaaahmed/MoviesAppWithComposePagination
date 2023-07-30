package com.movies.moviesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(stateHandle: SavedStateHandle
): ViewModel()
{
    private var movieNameData:MutableLiveData<String> = MutableLiveData()
    var _movieName:LiveData<String> = movieNameData

    private var posterPathData:MutableLiveData<String> = MutableLiveData()
    var _posterPathData:LiveData<String> = posterPathData

    private var overViewData:MutableLiveData<String> = MutableLiveData()
    var _overView:LiveData<String> = overViewData

    init{

        val movieName = stateHandle.get<String>("movie_name") ?: "Empty name"
        val posterPath = stateHandle.get<String>("poster_path") ?: "Empty name"
        val overView = stateHandle.get<String>("overview") ?: "Empty name"

        movieNameData.value = movieName
        posterPathData.value = posterPath
        overViewData.value = overView

        Log.d(TAG, "diaa movie name: $movieName $posterPath $overView")

    }
}