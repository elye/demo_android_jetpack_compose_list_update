package com.example.mycomposetodo

import android.support.v4.os.IResultReceiver.Default
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainTodoView(viewModel: MainViewModel) {
    val todoListState = viewModel.todoListFlow.collectAsState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var popupControl by remember { mutableStateOf(false) }

        Button(onClick = {
            viewModel.generateRandomTodo()
            scope.launch {
                lazyListState.scrollToItem(0)
            }
        }) {
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
            items(
                items = todoListState.value,
                key = { todoItem -> todoItem.id },
                itemContent = { item ->
                    val currentItem by rememberUpdatedState(item)
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                                viewModel.removeRecord(currentItem)
                                true
                            } else false
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier
                            .padding(vertical = 1.dp)
                            .animateItemPlacement(),
                        directions = setOf(
                            DismissDirection.StartToEnd,
                            DismissDirection.EndToStart
                        ),
                        dismissThresholds = { direction ->
                            FractionalThreshold(
                                if (direction == DismissDirection.StartToEnd) 0.66f else 0.50f
                            )
                        },
                        background = {
                            SwipeBackground(dismissState)
                        },
                        dismissContent = {
                            TodoItemRow(item, todoListState, viewModel)
                        }
                    )
                }
            )
        }
    }
}
