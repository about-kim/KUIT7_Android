package com.example.kuit7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreen(modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "연락처",
                fontSize = 20.sp, fontWeight = FontWeight.Bold)

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ContactScreenPreview() {
    ContactScreen()
}
