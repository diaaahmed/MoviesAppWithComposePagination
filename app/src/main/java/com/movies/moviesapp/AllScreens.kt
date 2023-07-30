package com.movies.moviesapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AllScreens(val route:String, val title:String, val icon:ImageVector)
{
   // object Home: AllScreens(route = "home_screen/{genre_id}", title = "Home", icon = Icons.Default.Home)
    object Home: AllScreens(route = "home_screen/{genre_id}/{genre_name}", title = "Home", icon = Icons.Default.Home)
    object Category: AllScreens(route = "cart_screen", title = "Category", icon = Icons.Default.ShoppingCart)
    object Details: AllScreens(route = "single_movie_screen/{movie_name}/{poster_path}/{overview}", title = "Details",
        icon = Icons.Default.Done)
}
