package com.kuit.android.data.api

import com.kuit.android.data.dto.PostDto
import retrofit2.http.GET

interface PostApiService {
    @GET("/posts")
    suspend fun getPosts(): List<PostDto>
}