package com.example.mycomposetodo

data class TodoItem(val id: Int, val title: String, var urgent: Boolean = false)