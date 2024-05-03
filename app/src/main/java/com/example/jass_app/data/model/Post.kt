package com.example.jass_app.data.model

import androidx.annotation.DrawableRes

data class Post(
    @DrawableRes val userImage: Int = 0,
    val userId: Int = -1,
    @DrawableRes val post: Int = 0
)