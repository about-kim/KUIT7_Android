package com.example.kuit7

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreen(modifier: Modifier = Modifier) {
    val callList = listOf<Call>(
        Call(
            image = R.drawable.call1,
            name = "박서연",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call2,
            name = "김건국",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call3,
            name = "홍길동",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call1,
            name = "박서연",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call2,
            name = "김건국",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call3,
            name = "홍길동",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call1,
            name = "박서연",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call2,
            name = "김건국",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call3,
            name = "홍길동",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call1,
            name = "박서연",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call2,
            name = "김건국",
            phone = "010-1234-5678"
        ),
        Call(
            image = R.drawable.call3,
            name = "홍길동",
            phone = "010-1234-5678"
        )
    )
    Column(Modifier.fillMaxSize()) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "연락처",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(height = 42.dp))

        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            items(callList) { call ->
                Column() {
                    Spacer(modifier.height(3.dp))
                    Row(
                        modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painterResource(call.image),
                            contentDescription = null,
                            modifier.size(63.dp)
                        )

                        Text(
                            text = call.name,
                            fontSize = 16.sp
                        )
                        Text(
                            text = call.phone,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier.height(10.dp))
                    HorizontalDivider(thickness = 1.dp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactScreenPreview() {
    ContactScreen()
}
