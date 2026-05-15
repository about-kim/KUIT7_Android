package com.kuit.android.data.repositoryimpl

import com.kuit.android.data.api.PostApiService
import com.kuit.android.data.dto.PostRequestDto
import com.kuit.android.data.dto.toDomain
import com.kuit.android.domain.repository.PostRepository
import com.kuit.android.domain.repository.model.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val postService: PostApiService
) : PostRepository {

    // 등록하거나 수정한 게시글을 임시 저장할 메모리 캐시
    private val cachedPosts = mutableMapOf<Int, Post>()

    override suspend fun getPosts(): List<Post> {
        val dtos = postService.getPosts()
        return dtos.map { it.toDomain() }
    }

    override suspend fun getPostById(id: Int): Post {
        // 1. 캐시에 데이터가 있으면 반환 (방금 등록한 101번 등)
        cachedPosts[id]?.let { return it }

        // 2. 캐시에 없으면 서버에서 조회
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
        val post = dto.toDomain()

        // 조회를 위해 캐시에 저장 (서버에는 실제로 저장되지 않으므로)
        cachedPosts[post.id] = post
        return post
    }

    override suspend fun updatePost(id: Int, title: String): Post {
        val requestDto = PostRequestDto(title = title)
        val dto = postService.updatePost(id, requestDto)
        val post = dto.toDomain()

        // 수정된 데이터를 캐시에 반영
        cachedPosts[id] = post
        return post
    }

    override suspend fun deletePost(id: Int): Boolean {
        return try {
            postService.deletePost(id)
            cachedPosts.remove(id) // 캐시에서도 삭제
            true
        } catch (e: Exception) {
            false
        }
    }
}
