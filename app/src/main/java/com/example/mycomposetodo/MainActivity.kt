package com.example.mycomposetodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.mycomposetodo.ui.theme.MyComposeTodoTheme


class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTodoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainTodoView(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainTodoView(viewModel: MainViewModel) {
    val todoListState = viewModel.todoListFlow.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { viewModel.generateRandomTodo() }) {
            Text("Randomly Generate Todo")
        }
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
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
