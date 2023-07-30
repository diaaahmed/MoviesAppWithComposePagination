package com.movies.moviesapp.screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.movies.domain.entity.Genre
import com.movies.moviesapp.R
import com.movies.moviesapp.component.Chip
import com.movies.moviesapp.component.ErrorHolder
import com.movies.moviesapp.component.LoadingIndicator
import com.movies.moviesapp.component.PlaceHolder
import com.movies.moviesapp.viewmodel.CategoryViewModel
import com.movies.moviesapp.viewmodel.MainViewModel
import java.util.Locale


@SuppressLint("UnrememberedMutableState")
@Composable
fun GenreList(
    genreViewModel: CategoryViewModel = hiltViewModel(),
    moviesViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController,
    viewModel: CategoryViewModel = viewModel(LocalContext.current as ComponentActivity)

) {

    val categories = genreViewModel.categories.value.genre

    if (genreViewModel.categories.value.isLoading) {
        LoadingIndicator()
    }
    else if (genreViewModel.categories.value.genre.isNotEmpty())
    {

        Column {
            Text(
                modifier = Modifier.padding(PaddingValues(8.dp)),
                text = "Choose Category : ",
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold
            )

            LazyRow {
                items(categories.size) { genre ->
                    Chip(
                        genre = categories[genre],
                        selected = genreViewModel.selectedGenre.value == categories[genre],
                        onSelected = {
                                if (genreViewModel.selectedGenre.value == categories[genre])
                                    genreViewModel.setSelectedGenre(Genre())
                                else
                                    genreViewModel.setSelectedGenre(categories[genre])

                        },
                        modifier = Modifier
                    )
                }
            }

            if (genreViewModel.selectedGenre.value.name.isNotEmpty())
            {
                moviesViewModel.getMoviesA(Locale.getDefault().language,
                    genreViewModel.selectedGenre.value.id.toString())
                MoviesList{
                        title,poster,overView->
                    navController.navigate("single_movie_screen/$title$poster/$overView")
                }
            } else {
                PlaceHolder(
                    text = "Please select category",
                    painter = painterResource(id = R.drawable.select)
                )
            }
        }

    }
    else if (genreViewModel.categories.value.error.isNotEmpty())
    {
        ErrorHolder(text = moviesViewModel.movies.value.error)
    }

}