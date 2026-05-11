package com.kuit.android.domain.repository.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
