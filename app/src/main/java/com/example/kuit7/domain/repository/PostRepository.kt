package com.kuit.android.domain.repository

import com.kuit.android.domain.repository.model.Post

interface PostRepository {
    suspend fun getPost(): List<Post>
}