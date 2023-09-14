package com.sih.graminshikshasahyog.model

data class CourseModel(
    val title: String,
    val description: String,
    val author: String = "",
    val dateCreated: String = "",
    val thumbnail: String = "",
    val code : String
)
