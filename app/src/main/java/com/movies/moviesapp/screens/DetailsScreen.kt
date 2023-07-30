package com.movies.moviesapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.movies.data.constant.Constant
import com.movies.moviesapp.component.CoilImage
import com.movies.moviesapp.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen()
{
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    val movieName = detailsViewModel._movieName.value
    val posterPath = detailsViewModel._posterPathData.value
    val overView = detailsViewModel._overView.value

    Column {

        CoilImage(
            data = Constant.imageBaseUrl + posterPath,
            contentDescription = "",
            modifier = Modifier.padding(top = 10.dp)
                .height(350.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentScale = ContentScale.FillBounds,
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = movieName.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = overView.toString(),
            fontSize = 12.sp,
            color = Color(0xFF141414),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            overflow = TextOverflow.Ellipsis

        )
    }
}