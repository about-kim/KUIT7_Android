package com.example.kuit7

data class news(
    val image: Int,
    val category: String,
    val topic: String,
    val author_image: Int,
    val author: String,
    val clock: Int,
    val time: String,
    val menu: Int

    //Int 타입: image, author_image, clock, menu
    //String 타입: category, topic, author, time
)