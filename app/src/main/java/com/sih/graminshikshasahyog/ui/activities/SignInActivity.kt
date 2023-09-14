package com.sih.graminshikshasahyog.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNewHere.setOnClickListener {
            newHere()
        }

        binding.btnSignIn.setOnClickListener {
            signIn()
        }

        binding.tvForgotPassword.setOnClickListener {
            forgotPassword()
        }

    }

    private fun forgotPassword() {

    }

    private fun newHere() {
        val intent = Intent(this, StudentSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signIn() {
        val email = binding.etSIEmail.text.toString().trim()
        val pass = binding.etSIPass.text.toString().trim()
        auth = FirebaseAuth.getInstance()
        if (email.isEmpty() == true or pass.isEmpty()) {
            Toast.makeText(this@SignInActivity, "Enter email and password!", Toast.LENGTH_SHORT)
                .show()
        } else {
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@SignInActivity,
                        "Credentials do not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}