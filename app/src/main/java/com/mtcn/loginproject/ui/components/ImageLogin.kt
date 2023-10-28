package com.mtcn.loginproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.mtcn.loginproject.R

@Composable
fun ImageLogin(modifier: Modifier = Modifier, painter: Painter) {

    val isDarkMode = isSystemInDarkTheme()

    val colorFilter = if (isDarkMode) {
        ColorFilter.tint(Color(ContextCompat.getColor(LocalContext.current, R.color.md_indigo_300)))
    } else {
        ColorFilter.tint(Color(ContextCompat.getColor(LocalContext.current, R.color.md_green_300)))
    }


    Image(
        painter = painter,
        contentDescription = "Image Login",
        modifier = modifier
            .fillMaxWidth()
            .size(150.dp),
        alignment = Alignment.Center,
        colorFilter = colorFilter // Apply the color filter for dark mode
    )
}