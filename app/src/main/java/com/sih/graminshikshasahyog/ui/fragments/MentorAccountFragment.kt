package com.sih.graminshikshasahyog.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentMentorAccountBinding
import com.sih.graminshikshasahyog.databinding.FragmentMentorSignInBinding
import com.sih.graminshikshasahyog.ui.activities.SignInActivity

class MentorAccountFragment : Fragment() {

    private lateinit var binding: FragmentMentorAccountBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorAccountBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        binding.btnMentorSignOut.setOnClickListener {
            auth.signOut()
            activity?.startActivity(Intent(requireContext(), SignInActivity::class.java))
            activity?.finish()
        }

        return binding.root
    }

}