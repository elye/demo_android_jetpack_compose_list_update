package com.example.mycomposetodo

import android.os.Bundle
import android.util.Log
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTodoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

val todoList = mutableStateListOf(
    TodoItem(0, "My First Task"),
    TodoItem(1, "My Second Task", true),
    TodoItem(2, "My Third Task"),
    TodoItem(3, "My Forth Task"),
    TodoItem(4, "My Fifth Task"),
    TodoItem(5, "My Sixth Task"),
    TodoItem(6, "My Seventh Task"),
    TodoItem(7, "My Eighth Task"),
    TodoItem(8, "My Ninth Task"),
    TodoItem(9, "My Tenth Task"),
    TodoItem(10, "My Eleventh Task"),
    TodoItem(11, "My Twelfth Task"),
    TodoItem(12, "My Thirteen Task"),
    TodoItem(13, "My Fourteen Task"),
    TodoItem(14, "My Fifteen Task"),
    TodoItem(15, "My Sixteen Task"),
    TodoItem(16, "My Seventeenth Task"),
    TodoItem(17, "My Eighteenth Task"),
    TodoItem(18, "My Nineteenth Task"),
    TodoItem(19, "My Twentieth Task"),
)

@Composable
fun Greeting(name: String) {
    val todoListState = remember { todoList }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var popupControl by remember { mutableStateOf(false) }
        Button(onClick = { popupControl = true }) {
            Text("Add New Record")
        }
        if (popupControl) {
            Popup(
                popupPositionProvider = WindowCenterOffsetPositionProvider(),
                onDismissRequest = { popupControl = false }
            ) {
                AddRecord { popupControl = false }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = todoListState, itemContent = { item ->
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
                            val index = todoListState.indexOf(item)
                            todoListState[index] = todoListState[index].copy(urgent = it)
                            Log.d("Track", "${todoList.toList()}")
                        }
                    )
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTodoTheme {
        Greeting("Android")
    }
}
