package com.sih.graminshikshasahyog.model

data class CommunityModel(
    val title: String,
    val size: String,
    val visibility: String,
    val logo: String = "",
    val communityId: String
)
