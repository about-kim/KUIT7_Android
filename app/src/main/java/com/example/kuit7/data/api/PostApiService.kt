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
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostDto

    @Headers("Content-Type: application/json")
    @POST("posts")
    suspend fun createPost(@Body post: PostRequestDto): PostDto

    @Headers("Content-Type: application/json")
    @PATCH("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: PostRequestDto): PostDto

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)
}
