package com.movies.moviesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.movies.moviesapp.component.TopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            val items = listOf(
                AllScreens.Home, AllScreens.Category
            )

            Scaffold(topBar = {
                TopBar(title = "Home page")
            },
                bottomBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    BottomNavigation {
                        items.forEach { screens ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        imageVector = screens.icon,
                                        contentDescription = null
                                    )
                                },
                                label = { Text(text = screens.title) },
                                selected = currentRoute == screens.route,
                                onClick = {

                                    if(screens.route.contains("home"))
                                    {
                                        navController.navigate("home_screen/0/empty") {
                                            // To home back directly

                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                     //       restoreState = true

                                        }
                                    }
                                    else
                                    {
                                        navController.navigate(screens.route) {
                                            // To home back directly
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true

                                        }
                                    }

                                }
                            )
                        }
                    }
                }) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                    GetAndSetMovies(navController)
                }
            }

        }
    }

    @Composable
    fun GetAndSetMovies(navController: NavHostController)
    {
        Nav(navController = navController)
    }
}
