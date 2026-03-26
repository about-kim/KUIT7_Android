package com.example.kuit7

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    val newsLists = listOf<news>(
        news(
            image = R.drawable.image1,
            category = "Europe",
            topic = "Ukraine's President Zelensky to BBC: Blood money being paid...",
            author_image = R.drawable.bbc,
            author = "BBC News",
            clock = R.drawable.clock,
            time = "14m ago",
            menu = R.drawable.point
        ),
        news(
            image = R.drawable.image2,
            category = "Travel",
            topic = "Her train broke down. Her phone died. And then she met her...",
            author_image = R.drawable.cnn,
            author = "CNN",
            clock = R.drawable.clock,
            time = "1h ago",
            menu = R.drawable.point
        ),
        news(
            image = R.drawable.image3,
            category = "Europe",
            topic = "Russian warship: Moskva sinks in Black Sea",
            author_image = R.drawable.bbc,
            author = "BBC News",
            clock = R.drawable.clock,
            time = "4h ago",
            menu = R.drawable.point
        ),
        news(
            image = R.drawable.image4,
            category = "Money",
            topic = "Wind power produced more electricity than coal and nucle...",
            author_image = R.drawable.usa,
            author = "USA Today",
            clock = R.drawable.clock,
            time = "4h ago",
            menu = R.drawable.point
        ),
        news(
            image = R.drawable.image5,
            category = "Life",
            topic = "'We keep rising to new challenges.' For churches hit by...",
            author_image = R.drawable.usa,
            author = "USA Today",
            clock = R.drawable.clock,
            time = "4h ago",
            menu = R.drawable.point
        ),
        news(
            image = R.drawable.image1,
            category = "Europe",
            topic = "Ukraine's President Zelensky to BBC: Blood money being paid...",
            author_image = R.drawable.bbc,
            author = "BBC News",
            clock = R.drawable.clock,
            time = "14m ago",
            menu = R.drawable.point
        ),
        news(
            image = R.drawable.image2,
            category = "Travel",
            topic = "Her train broke down. Her phone died. And then she met her...",
            author_image = R.drawable.cnn,
            author = "CNN",
            clock = R.drawable.clock,
            time = "1h ago",
            menu = R.drawable.point
        )
    )

    val categories = listOf("All", "Sports", "Politics", "Business", "Health", "Travel", "Science", "Fashion")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        // 상단 로고
        Row(
            modifier = Modifier
                .width(380.dp)
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(R.drawable.ka),
                contentDescription = "logo",
                modifier = Modifier
                    .width(99.dp)
                    .height(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Latest + See all
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Latest",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(0f, 10f),
                        blurRadius = 10f
                    )
                )
            )

            Text(
                text = "See all",
                fontSize = 14.sp,
                color = Color(0xFF4E4B66)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 카테고리 탭
        LazyRow(
            modifier = Modifier
                .height(48.dp),
                verticalAlignment = Alignment.Top,
            contentPadding = PaddingValues(horizontal = 17.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                Text(
                    text = category,
                    fontSize = 16.sp,
                    color = if (category == "All") Color.Black else Color(0xFF4E4B66)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 뉴스 목록
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(newsLists) { item ->
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    // 뉴스 이미지
                    Image(
                        painter = painterResource(item.image),
                        contentDescription = "기사 사진",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    // 텍스트 영역
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        // 카테고리
                        Text(
                            text = item.category,
                            fontSize = 12.sp,
                            color = Color(0xFF4E4B66)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // 제목
                        Text(
                            text = item.topic,
                            fontSize = 14.sp,
                            color = Color.Black,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // 하단 정보 (author + time + menu)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // 언론사 로고
                                Image(
                                    painter = painterResource(item.author_image),
                                    contentDescription = "언론사 로고",
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = item.author,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF4E4B66)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                // 시계 아이콘
                                Image(
                                    painter = painterResource(item.clock),
                                    contentDescription = "시계",
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = item.time,
                                    fontSize = 12.sp,
                                    color = Color(0xFF4E4B66)
                                )
                            }
                            // 더보기 메뉴
                            Image(
                                painter = painterResource(item.menu),
                                contentDescription = "더보기 메뉴",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() {
    NewsScreen()
}
