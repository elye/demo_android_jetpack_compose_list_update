package com.example.mycomposetodo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddRecord(closeRecord: () -> Unit, viewModel: MainViewModel) {
    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xCCEEEEEE),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var titleText by rememberSaveable { mutableStateOf("") }
            var checkBoxStatus by rememberSaveable { mutableStateOf(false) }
            TextField(
                value = titleText,
                onValueChange = { titleText = it },
                label = { Text("Title") }
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Urgency:")
                Checkbox(
                    checked = checkBoxStatus,
                    onCheckedChange = {
                        checkBoxStatus = !checkBoxStatus
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextButton(onClick = {
                if (titleText != "") {
                    viewModel.addRecord(titleText, checkBoxStatus)
                    closeRecord.invoke()
                }
            }) {
                Text(text = "Submit Record")
            }
        }
    }
}

