package com.kuit.android.domain.repository

import com.kuit.android.domain.repository.model.Post

interface PostRepository {
    // 전체 게시글 목록 조회
    suspend fun getPosts(): List<Post>

    // 단일 게시글 조회
    suspend fun getPostById(id: Int): Post

    // 게시글 등록
    suspend fun createPost(title: String, body: String, userId: Int): Post

    // 게시글 수정
    suspend fun updatePost(id: Int, title: String): Post

    // 게시글 삭제
    suspend fun deletePost(id: Int): Boolean
}