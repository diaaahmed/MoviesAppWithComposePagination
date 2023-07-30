package com.movies.moviesapp.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movies.data.constant.Constant
import com.movies.domain.entity.Genre
import com.movies.domain.entity.MovieItem

@Composable
fun MovieItem(movie: MovieItem, click:(String,String,String) -> Unit ) {

    Column(
        modifier = Modifier.clickable {
            click(movie.title,movie.poster_path,movie.overview)
        }
    ) {


        CoilImage(
            data = Constant.imageBaseUrl + movie.poster_path,
            contentDescription = movie.title,
            modifier = Modifier,
            contentScale = ContentScale.FillBounds,
        )

        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,

        )
    }
}

@Composable
fun GenreItem(genre: Genre, click: (Int,String) -> Unit) {

    Column(
        modifier = Modifier.clickable {
            click(genre.id,genre.name)
        }
    ) {

        Text(
            text = genre.name,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,

            )
    }
}