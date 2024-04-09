package com.example.notesapp.data.classes

import androidx.annotation.DrawableRes
import com.example.notesapp.R

data class Page(
    val title: String,
    val desc: String,
    @DrawableRes val image: Int
)

val onBoardingPages = listOf(
    Page(
        title = "Welcome",
        desc = "A personal vault for you to store your thoughts",
        image = R.drawable.page1
    ),
    Page(
        title = "Speak",
        desc = "Voice your thoughts into the capsules of the vault",
        image = R.drawable.page1
    ),
    Page(
        title = "Illustrate",
        desc = "Explain thoughts with help of images",
        image = R.drawable.page1
    )

)
