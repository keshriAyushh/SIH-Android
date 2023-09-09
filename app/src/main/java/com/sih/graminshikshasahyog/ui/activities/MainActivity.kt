package com.sih.graminshikshasahyog.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sih.graminshikshasahyog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Landing page for student sign up
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}