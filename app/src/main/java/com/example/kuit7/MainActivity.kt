package com.example.kuit7 //test3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuit7.ui.theme.KUIT7week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KUIT7week1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(paddingValues = innerPadding)
                            .padding(top = 50.dp, start = 50.dp)
                            .size(300.dp)
                            .border(
                                width = 2.dp, color = Color.Black, shape = RoundedCornerShape(15.dp)
                            )
                            .background(Color.White)
                    ) {
                        Column(
                            Modifier.padding(top = 20.dp, start = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(1.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.picture),
                                contentDescription = "프로필 사진",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(30.dp))
                            )
                            Text("이름: 김대한", fontSize = 15.sp)
                            Text("학번: 202415176", fontSize = 15.sp)
                            Text("학과: 컴퓨터공학부", fontSize = 15.sp)
                            Text("생년월일: 2002/05/22", fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }
}
