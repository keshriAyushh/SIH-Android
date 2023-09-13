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
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var signinBtn: Button
    private lateinit var signupIntent: TextView
    private lateinit var forgotpasswordbtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        intializer()
        signIn()
        newHere()
        forgotpassword()

    }

    private fun forgotpassword() {

    }

    private fun newHere() {
        signupIntent.setOnClickListener{
            val intent = Intent(this, StudentSignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signIn() {
        signinBtn.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()
            if(email.isEmpty()==true or pass.isEmpty()==true){
                Toast.makeText(applicationContext,"Enter email and password!",Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful) {
//                        val intent = Intent(this, MainActivity::class.java)
                        Toast.makeText(applicationContext,"Logged In",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Credentials do not match", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun intializer() {
        auth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.etSIEmail)
        etPassword = findViewById(R.id.etSIPass)
        signinBtn = findViewById(R.id.btnSignIn)
        signupIntent = findViewById(R.id.tvNewHere)
        forgotpasswordbtn = findViewById(R.id.textView)

    }

}