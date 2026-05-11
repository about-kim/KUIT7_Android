package com.kuit.android.data.dto

import com.kuit.android.domain.repository.model.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun PostDto.toDomain(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)