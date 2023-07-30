package com.movies.moviesapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movies.moviesapp.screens.CategoryScreen
import com.movies.moviesapp.screens.DetailsScreen
import com.movies.moviesapp.screens.GenreList
import com.movies.moviesapp.viewmodel.CategoryViewModel


@Composable
fun Nav(navController: NavHostController)
{
    val viewModel: CategoryViewModel = hiltViewModel()


    NavHost(navController = navController, startDestination = AllScreens.Home.route)
    {

        composable(AllScreens.Home.route, arguments = listOf(
            navArgument("genre_id")
            {
                type = NavType.IntType
            },
            navArgument("genre_name")
            {
                type = NavType.StringType
            }
        ))
        {
            GenreList(navController = navController, genreViewModel = viewModel)
        }

        composable(AllScreens.Category.route)
        {
            CategoryScreen(navController = navController, genreViewModel = viewModel)
        }


        composable(AllScreens.Details.route, arguments = listOf(
            navArgument("movie_name")
            {
                type = NavType.StringType
            },

            navArgument("poster_path")
            {
                type = NavType.StringType
            },
            navArgument("overview")
            {
                type = NavType.StringType
            }
        ))
        {
            DetailsScreen()
        }
    }
}