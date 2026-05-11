package com.kuit.android.data.repositoryimpl

import com.kuit.android.data.api.PostApiService
import com.kuit.android.data.dto.toDomain
import com.kuit.android.domain.repository.PostRepository
import com.kuit.android.domain.repository.model.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostApiService
): PostRepository {
    override suspend fun getPost(): List<Post> {
        val dto = postService.getPosts()

        return dto.map {it.toDomain()}
    }
}