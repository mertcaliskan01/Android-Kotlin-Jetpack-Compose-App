package com.mtcn.loginproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mtcn.loginproject.R

@Composable
fun SocialMediaIcons(
    onclickFacebook: () -> Unit,
    onclickLinkedin: () -> Unit,
    onclickGoogle: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(70.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialMediaIcon(painterResource(id = R.drawable.facebook), "Facebook", onclickFacebook)
        SocialMediaIcon(painterResource(id = R.drawable.linkedin), "Linkedin", onclickLinkedin)
        SocialMediaIcon(painterResource(id = R.drawable.google_plus), "Google", onclickGoogle)
    }
}

@Composable
fun SocialMediaIcon(icon: Painter, text: String, onclick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onclick() }
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp
            )
        )
    }
}