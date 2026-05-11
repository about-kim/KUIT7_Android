package com.kuit.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.android.ui.state.PostUiState
import com.kuit.android.ui.viewmodel.PostViewModel

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var postId by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "JSONPlaceholder API 테스트",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 입력 필드
        OutlinedTextField(
            value = postId,
            onValueChange = { postId = it },
            label = { Text("Post ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Body") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // API 버튼들
        Text(
            text = "API 작업",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // GET /posts - 전체 게시글 조회
        Button(
            onClick = { viewModel.fetchPosts() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("1. 전체 게시글 조회 (GET /posts)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // GET /posts/{id} - 단일 게시글 조회
        Button(
            onClick = {
                postId.toIntOrNull()?.let { id ->
                    viewModel.fetchPostById(id)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = postId.toIntOrNull() != null
        ) {
            Text("2. 단일 게시글 조회 (GET /posts/{id})")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // POST /posts - 게시글 등록
        Button(
            onClick = {
                val userIdInt = userId.toIntOrNull()
                if (title.isNotBlank() && body.isNotBlank() && userIdInt != null) {
                    viewModel.createPost(title, body, userIdInt)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = title.isNotBlank() && body.isNotBlank() && userId.toIntOrNull() != null
        ) {
            Text("3. 게시글 등록 (POST /posts)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // PATCH /posts/{id} - 게시글 수정
        Button(
            onClick = {
                postId.toIntOrNull()?.let { id ->
                    if (title.isNotBlank()) {
                        viewModel.updatePost(id, title)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = postId.toIntOrNull() != null && title.isNotBlank()
        ) {
            Text("4. 게시글 수정 (PATCH /posts/{id})")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // DELETE /posts/{id} - 게시글 삭제
        Button(
            onClick = {
                postId.toIntOrNull()?.let { id ->
                    viewModel.deletePost(id)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = postId.toIntOrNull() != null,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("5. 게시글 삭제 (DELETE /posts/{id})")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        // 결과 표시 영역
        Text(
            text = "결과",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        when (val state = uiState) {
            PostUiState.Idle -> {
                Text(
                    text = "버튼을 눌러 API를 테스트하세요.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            PostUiState.Loading -> {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("로딩 중...")
            }

            is PostUiState.PostListSuccess -> {
                Text(
                    text = "전체 게시글 (${state.posts.size}개)",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(8.dp)
                    ) {
                        items(state.posts) { post ->
                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                Text(
                                    text = "[${post.id}] ${post.title}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "User ID: ${post.userId}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            if (post != state.posts.last()) {
                                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                            }
                        }
                    }
                }
            }

            is PostUiState.SinglePostSuccess -> {
                Text(
                    text = "단일 게시글 조회 성공",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("ID: ${state.post.id}")
                        Text("User ID: ${state.post.userId}")
                        Text("Title: ${state.post.title}")
                        Text("Body: ${state.post.body}")
                    }
                }
            }

            is PostUiState.CreateSuccess -> {
                Text(
                    text = "게시글 등록 성공",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("생성된 ID: ${state.post.id}")
                        Text("User ID: ${state.post.userId}")
                        Text("Title: ${state.post.title}")
                        Text("Body: ${state.post.body}")
                    }
                }
            }

            is PostUiState.UpdateSuccess -> {
                Text(
                    text = "게시글 수정 성공",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("ID: ${state.post.id}")
                        Text("User ID: ${state.post.userId}")
                        Text("Updated Title: ${state.post.title}")
                        Text("Body: ${state.post.body}")
                    }
                }
            }

            is PostUiState.DeleteSuccess -> {
                Text(
                    text = "게시글 삭제 성공",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("삭제된 Post ID: ${state.id}")
                    }
                }
            }

            is PostUiState.Error -> {
                Text(
                    text = "오류 발생",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = state.message,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}