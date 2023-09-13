package com.sih.graminshikshasahyog.model

data class Student(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val gender: String,
    val dob: String,
    val qualification: String,
    val preference: String,
    val profilePicture: String = ""
)
