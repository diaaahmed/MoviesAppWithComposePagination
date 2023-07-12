package com.movies.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.movies.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    getAndSetMovies()
                }
            }
        }
    }

    @Composable
    fun getAndSetMovies() {
        Column()
        {

            mainViewModel.getMoviesA(Locale.getDefault().language, "53")

            val movies = mainViewModel.movies.value.movies!!.flow.collectAsLazyPagingItems()

            if(mainViewModel.movies.value.isLoading)
            {
                println("Diaa data is loading")
            }
            else if(mainViewModel.movies.value.error.isNotEmpty())
            {
                println("Diaa data error ${mainViewModel.movies.value.error}")

            }
            else{

                LazyVerticalGrid(columns = GridCells.Fixed(2))
                {
                    items(movies.itemCount){index->
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize(),
                        ) {

                            MovieItem(movies[index]!!)
                        }
                    }
                }
            }


        }

    }
}
