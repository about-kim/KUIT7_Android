package com.kuit.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.android.ui.state.PostUiState
import com.kuit.android.ui.viewmodel.PostViewmodel

@Composable
fun PostScreen(modifier: Modifier = Modifier, viewmodel: PostViewmodel = hiltViewModel()) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { viewmodel.fetchPosts() }) {
            Text(text = "조회")
        }
        Spacer(modifier = Modifier.height(10.dp))

        when (val state = uiState) {
            PostUiState.Idle -> {
                Text(text = "버튼을 눌러주세요.")
            }

            PostUiState.Loading -> {
                Text(text = "로딩 중입니다.")
            }

            is PostUiState.Success -> {
                LazyColumn {
                    items(state.posts) { post ->
                        Text(text = post.title)
                    }
                }
            }

            is PostUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}
