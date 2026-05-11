package com.kuit.android.data.api

import com.kuit.android.data.dto.PostDto
import com.kuit.android.data.dto.PostRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApiService {
    // 명세서 2 - 전체 게시글 목록 조회
    @GET("/posts")
    suspend fun getPosts(): List<PostDto>

    // 명세서 1 - 단일 게시글 조회
    @GET("/posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostDto

    // 명세서 3 - 게시글 등록
    @Headers("Content-Type: application/json")
    @POST("/posts")
    suspend fun createPost(@Body post: PostRequestDto): PostDto

    // 명세서 4 - 게시글 수정
    @Headers("Content-Type: application/json")
    @PATCH("/posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: PostRequestDto): PostDto

    // 명세서 5 - 게시글 삭제
    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)
}