package com.example.mycomposetodo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddRecord(closeRecord: () -> Unit) {
    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xCCEEEEEE),
    ) {
        Column(
            modifier = Modifier.padding(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "I'm popping up")
            Spacer(modifier = Modifier.height(32.dp))
            TextButton(onClick = closeRecord) {
                Text(text = "Close Popup")
            }
        }
    }
}
