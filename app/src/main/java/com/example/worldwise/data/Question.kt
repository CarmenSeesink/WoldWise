package com.example.worldwise.data

data class Question (
    val question: String,
    val image: String,
    val categoryId: Int,
    val answers: List<String>,
    val correctAnswer : String
)