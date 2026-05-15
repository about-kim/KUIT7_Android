package com.kuit.android.data.di

import com.kuit.android.data.api.PostApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideJson() : Json = Json{
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
    }
    @Provides
    @Singleton
    fun provideRetrofit(json: Json) : Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType()),
            ).build()

    @Provides
    @Singleton
    fun providePostApiService(retrofit: Retrofit) : PostApiService =
        retrofit.create(PostApiService::class.java)
}
//