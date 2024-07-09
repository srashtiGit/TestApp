package com.example.myapplication.model

import java.io.Serializable

data class BookListItem(
    val author: String,
    val currencyCode: String,
    val id: Int,
    val isbn: String,
    val price: Int,
    val title: String,
    val description: String
):Serializable