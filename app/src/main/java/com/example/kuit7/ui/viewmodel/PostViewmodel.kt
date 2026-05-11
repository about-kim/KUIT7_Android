package com.kuit.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.android.domain.repository.PostRepository
import com.kuit.android.ui.state.PostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.Idle)
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    // 전체 게시글 목록 조회
    fun fetchPosts() {
        _uiState.value = PostUiState.Loading
        viewModelScope.launch {
            try {
                val posts = postRepository.getPosts()
                _uiState.value = PostUiState.PostListSuccess(posts)
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "게시글 목록 조회 실패")
            }
        }
    }

    // 단일 게시글 조회
    fun fetchPostById(id: Int) {
        _uiState.value = PostUiState.Loading
        viewModelScope.launch {
            try {
                val post = postRepository.getPostById(id)
                _uiState.value = PostUiState.SinglePostSuccess(post)
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "게시글 조회 실패")
            }
        }
    }

    // 게시글 등록
    fun createPost(title: String, body: String, userId: Int) {
        _uiState.value = PostUiState.Loading
        viewModelScope.launch {
            try {
                val post = postRepository.createPost(title, body, userId)
                _uiState.value = PostUiState.CreateSuccess(post)
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "게시글 등록 실패")
            }
        }
    }

    // 게시글 수정
    fun updatePost(id: Int, title: String) {
        _uiState.value = PostUiState.Loading
        viewModelScope.launch {
            try {
                val post = postRepository.updatePost(id, title)
                _uiState.value = PostUiState.UpdateSuccess(post)
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "게시글 수정 실패")
            }
        }
    }

    // 게시글 삭제
    fun deletePost(id: Int) {
        _uiState.value = PostUiState.Loading
        viewModelScope.launch {
            try {
                val success = postRepository.deletePost(id)
                if (success) {
                    _uiState.value = PostUiState.DeleteSuccess(id)
                } else {
                    _uiState.value = PostUiState.Error("게시글 삭제 실패")
                }
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "게시글 삭제 실패")
            }
        }
    }
}