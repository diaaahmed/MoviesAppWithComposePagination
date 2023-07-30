package com.movies.moviesapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

import com.movies.moviesapp.component.LoadingIndicator
import com.movies.moviesapp.component.MovieItem
import com.movies.moviesapp.viewmodel.MainViewModel

@Composable
fun MoviesList(
    mainViewModel: MainViewModel = hiltViewModel(),
    click: (String, String, String?) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val movies = mainViewModel.movies.value.movies!!.flow.collectAsLazyPagingItems()

        if (mainViewModel.movies.value.isLoading) {
            LoadingIndicator()
        } else if (mainViewModel.movies.value.error.isNotEmpty()) {
            println("Diaa data error ${mainViewModel.movies.value.error}")

        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2))
            {
                items(movies.itemCount) { index ->
                    Card(
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize(),
                        elevation = 15.dp,
                    ) {

                        MovieItem(movies[index]!!, click)
                    }
                }
            }

        }
    }
}
