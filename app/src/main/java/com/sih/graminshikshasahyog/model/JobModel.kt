package com.sih.graminshikshasahyog.model

import com.google.api.OAuthRequirements

data class JobModel(
    val title: String,
    val description: String,
    val jobId: String,
    val company: String,
    val salary: String,
    val timings: String,
    val requirements: String
)
