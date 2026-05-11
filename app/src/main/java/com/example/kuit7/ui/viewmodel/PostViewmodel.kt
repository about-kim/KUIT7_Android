package com.kuit.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.android.domain.repository.PostRepository
import com.kuit.android.ui.state.PostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PostViewmodel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.Idle)
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    fun fetchPosts() {
        _uiState.value = PostUiState.Loading
        viewModelScope.launch {
            try {
                val posts = postRepository.getPost()
                _uiState.value = PostUiState.Success(posts)
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}