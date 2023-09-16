package com.sih.graminshikshasahyog.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentMentorAccountBinding
import com.sih.graminshikshasahyog.databinding.FragmentMentorSignInBinding
import com.sih.graminshikshasahyog.ui.activities.SignInActivity

class MentorAccountFragment : Fragment() {

    private lateinit var binding: FragmentMentorAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorAccountBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        firestore.collection("mentoruserDB")
            .document(auth.currentUser?.uid!!)
            .get()
            .addOnSuccessListener {
                if(it.exists()) {
                    binding.tvMentorName.text = it.data?.get("name")?.toString() ?: "null"
                    binding.tvMentorGenderValue.text = it.data?.get("gender")?.toString() ?: "null"
                    binding.tvMentorPhoneValue.text = it.data?.get("phone")?.toString() ?: "null"
                    binding.tvMentorDOBValue.text = it.data?.get("dob")?.toString() ?: "null"
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error fetching data!", Toast.LENGTH_SHORT)
                    .show()
            }

        binding.btnMentorSignOut.setOnClickListener {
            auth.signOut()
            activity?.startActivity(Intent(requireContext(), SignInActivity::class.java))
            activity?.finish()
        }

        return binding.root
    }

}