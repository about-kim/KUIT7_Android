package com.kuit.android.data.repositoryimpl

import com.kuit.android.data.api.PostApiService
import com.kuit.android.data.dto.PostRequestDto
import com.kuit.android.data.dto.toDomain
import com.kuit.android.domain.repository.PostRepository
import com.kuit.android.domain.repository.model.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostApiService
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        val dtos = postService.getPosts()
        return dtos.map { it.toDomain() }
    }

    override suspend fun getPostById(id: Int): Post {
        val dto = postService.getPost(id)
        return dto.toDomain()
    }

    override suspend fun createPost(title: String, body: String, userId: Int): Post {
        val requestDto = PostRequestDto(
            title = title,
            body = body,
            userId = userId
        )
        val dto = postService.createPost(requestDto)
        return dto.toDomain()
    }

    override suspend fun updatePost(id: Int, title: String): Post {
        val requestDto = PostRequestDto(title = title)
        val dto = postService.updatePost(id, requestDto)
        return dto.toDomain()
    }

    override suspend fun deletePost(id: Int): Boolean {
        return try {
            postService.deletePost(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}