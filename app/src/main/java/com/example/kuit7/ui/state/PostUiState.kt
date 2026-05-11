package com.kuit.android.ui.state

import com.kuit.android.domain.repository.model.Post

sealed interface PostUiState {
    data object Idle : PostUiState
    data object Loading : PostUiState
    data class PostListSuccess(val posts: List<Post>) : PostUiState
    data class SinglePostSuccess(val post: Post) : PostUiState
    data class CreateSuccess(val post: Post) : PostUiState
    data class UpdateSuccess(val post: Post) : PostUiState
    data class DeleteSuccess(val id: Int) : PostUiState
    data class Error(val message: String) : PostUiState
}