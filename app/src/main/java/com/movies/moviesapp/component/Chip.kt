package com.movies.moviesapp.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.movies.domain.entity.Genre

@Composable
fun Chip(
    genre: Genre,
    modifier: Modifier,
    selected: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    onSelected: ((movie: Genre) -> Unit),

    ) {
    Surface(
        modifier = modifier.clickable {
            onSelected(genre)
        }.padding(contentPadding),
        shape = RoundedCornerShape(10.dp),
        color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        elevation = if (selected) 5.dp else 0.dp
    ) {
        Text(
            modifier = modifier.padding(contentPadding),
            text = genre.name,
            style = MaterialTheme.typography.body2,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
        Divider()
    }
}