package com.sih.graminshikshasahyog.model

data class MentorItemModel(
    val title: String,
    val description: String,
    val pfp: String = "",
    val mentorId: String
)
