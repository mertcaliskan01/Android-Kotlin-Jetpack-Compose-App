package com.mtcn.loginproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mtcn.loginproject.R

@Composable
fun ProgressBarLoading(
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {
    if (isLoading) {
        Column(
            modifier = modifier
                .background(Color.DarkGray.copy(alpha = 0.3f))
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = colorResource(id = R.color.md_green_300),
                strokeWidth = 5.dp,
                modifier = modifier.size(60.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProgressBarLoadingPreview() {
    ProgressBarLoading(isLoading = true) // You can change isLoading to false if needed
}