package com.movies.moviesapp.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.movies.domain.entity.Genre
import com.movies.moviesapp.component.GenreItem
import com.movies.moviesapp.component.LoadingIndicator
import com.movies.moviesapp.viewmodel.CategoryViewModel


@Composable
fun CategoryScreen(navController: NavHostController,viewModel: CategoryViewModel = viewModel(
    LocalContext.current as ComponentActivity
),
                   genreViewModel: CategoryViewModel = hiltViewModel(),

                   )
{
    val categoryViewModel: CategoryViewModel = hiltViewModel()
     // categoryViewModel.getCategories("ar")

    val categories = categoryViewModel.categories.value.genre

    Column() {

        if(categoryViewModel.categories.value.isLoading)
        {
            LoadingIndicator()
        }
        else if(categoryViewModel.categories.value.error.isNotEmpty())
        {
            println("Diaa data error ${categoryViewModel.categories.value.error}")

        }
        else
        {
            LazyVerticalGrid(columns = GridCells.Fixed(2))
            {
                items(categories.size) {
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize(),
                        elevation = 15.dp,
                    ) {
                        GenreItem(categories[it]){
                            id,name->
                            genreViewModel.setSelectedGenre(Genre(id,name))
                           // click(it)
                            navController.navigate("home_screen/$id/$name")
                        }
                    }
                }
            }
        }
    }
}