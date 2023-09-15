package com.sih.graminshikshasahyog.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.sih.graminshikshasahyog.databinding.FragmentMentorSignInBinding
import com.sih.graminshikshasahyog.ui.activities.ForgotPassActivity
import com.sih.graminshikshasahyog.ui.activities.MentorActivity
import com.sih.graminshikshasahyog.ui.activities.MentorSignUpActivity
import com.sih.graminshikshasahyog.ui.activities.StudentSignUpActivity

class MentorSignInFragment : Fragment() {

    private lateinit var binding: FragmentMentorSignInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMentorSignInBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        binding.btnSignIn.setOnClickListener {
            if(binding.etSIEmail.text.toString().isNotEmpty()) {
                if(binding.etSIPass.text.toString().isNotEmpty()) {
                    signInWithEmailAndPassword(binding.etSIEmail.text.toString(), binding.etSIPass.text.toString())
                } else {
                    Toast.makeText(requireContext(), "Please enter your Password!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter your email!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.tvNewHere.setOnClickListener {
            startActivity(Intent(requireContext(), MentorSignUpActivity::class.java))
            activity?.finish()
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(requireContext(), ForgotPassActivity::class.java))
        }

        return binding.root
    }

    private fun signInWithEmailAndPassword(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                startActivity(Intent(requireContext(), MentorActivity::class.java))
                activity?.finish()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
            }
    }

}