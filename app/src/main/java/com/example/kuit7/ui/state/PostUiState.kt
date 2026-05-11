package com.kuit.android.ui.state

import com.kuit.android.domain.repository.model.Post

sealed interface PostUiState {
    data object Idle : PostUiState
    data object Loading : PostUiState
    data class Success(val posts: List<Post>) : PostUiState
    data class Error(val message: String) : PostUiState
}