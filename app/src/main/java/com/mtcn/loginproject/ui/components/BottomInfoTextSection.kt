package com.mtcn.loginproject.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomInfoTextSection(
    onclick: () -> Unit,
    normalText : String,
    boldText : String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = normalText,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { /* Handle new user click */ }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = boldText,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.clickable {
                    onclick() // Invoke the onclick lambda when clicked
                }
            )
        }

    }
}