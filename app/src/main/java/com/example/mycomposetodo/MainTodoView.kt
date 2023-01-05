package com.example.mycomposetodo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.launch

@Composable
fun MainTodoView(viewModel: MainViewModel) {
    val todoListState = viewModel.todoListFlow.collectAsState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var popupControl by remember { mutableStateOf(false) }

        Button(onClick = { viewModel.generateRandomTodo() }) {
            Text("Randomly Generate Todo")
        }
        Button(onClick = { popupControl = true }) {
            Text("Add Record")
        }
        if (popupControl) {
            Popup(
                popupPositionProvider = WindowCenterOffsetPositionProvider(),
                onDismissRequest = { popupControl = false },
                properties = PopupProperties(focusable = true)
            ) {
                AddRecord({
                    popupControl = false
                    scope.launch {
                        lazyListState.scrollToItem(todoListState.value.size)
                    }
                }, viewModel)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            state = lazyListState
        ) {
            items(items = todoListState.value, itemContent = { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        text = item.title
                    )
                    Checkbox(
                        checked = item.urgent,
                        onCheckedChange = {
                            val index = todoListState.value.indexOf(item)
                            viewModel.setUrgent(index, it)
                        }
                    )
                }
            })
        }
    }
}
