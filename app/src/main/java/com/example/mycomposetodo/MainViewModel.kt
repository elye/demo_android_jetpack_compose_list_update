package com.example.mycomposetodo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private var todoList = mutableStateListOf(
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
    private val _todoListFlow = MutableStateFlow(todoList)

    val todoListFlow: StateFlow<List<TodoItem>> get() = _todoListFlow

    fun setUrgent(index: Int, value: Boolean) {
        todoList[index] = todoList[index].copy(urgent = value)
    }

    fun generateRandomTodo() {
        val numberOfTodo = (10..20).random()
        val mutableTodoList = mutableStateListOf<TodoItem>()
        (0..numberOfTodo).forEach {
            mutableTodoList.add(TodoItem(it, "Item $it: ${randomWord()}", Random.nextBoolean()))
        }
        todoList = mutableTodoList
        _todoListFlow.value = mutableTodoList
    }

    private fun randomWord(): String {
        val random = Random
        val sb = StringBuilder()
        for (i in 1..random.nextInt(10) + 5) {
            sb.append(('a' + random.nextInt(26)))
        }
        return sb.toString()
    }
}
