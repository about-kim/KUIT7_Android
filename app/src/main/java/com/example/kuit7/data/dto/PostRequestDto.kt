package com.kuit.android.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestDto(
    val title: String? = null,
    val body: String? = null,
    val userId: Int? = null
)