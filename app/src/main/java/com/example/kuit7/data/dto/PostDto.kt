package com.kuit.android.data.dto

import com.kuit.android.domain.repository.model.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    @SerialName("userId")
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
)

fun PostDto.toDomain(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)
