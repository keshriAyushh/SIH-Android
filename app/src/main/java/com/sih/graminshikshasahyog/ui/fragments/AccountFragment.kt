package com.sih.graminshikshasahyog.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentAccountBinding
import com.sih.graminshikshasahyog.ui.activities.SignInActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

//        val student: MutableMap<String, Any> = getStudentDetails()

        val collectionref: CollectionReference = firestore.collection("studentuserDB")
        val documentref = collectionref.document(auth.currentUser?.uid!!)

        documentref.get()
            .addOnSuccessListener {
                if(it.exists()) {
                    Log.d("data", it.data?.get("name").toString())
                    binding.tvName.text = it.data?.get("name")?.toString() ?: "null"
                    binding.tvGenderValue.text = it.data?.get("gender")?.toString() ?: "null"
                    binding.tvPhoneValue.text = it.data?.get("phone")?.toString()?.subSequence(3, 13) ?: "null"
                    binding.tvDOBValue.text = it.data?.get("dob")?.toString() ?: "null"


                    Glide.with(binding.profilePicture.context)
                        .load(it.data?.get("profilePicture").toString())
                        .placeholder(R.drawable.user)
                        .skipMemoryCache(false)//for caching the image url in case phone is offline
                        .into(binding.profilePicture)
                }
            }
            .addOnFailureListener {

            }

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
            activity?.finish()
        }

        return binding.root
    }

    private fun currentUser(): String {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        return user?.uid ?: "null"
    }

    private fun getStudentDetails(): MutableMap<String, Any> {
        //TODO: Fetch user details here and return it to display its details
        val userid = auth.currentUser?.uid!!
        var data: MutableMap<String, Any> = mutableMapOf<String, Any>()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionref: CollectionReference = firestore.collection("studentuserDB")
        val documentref = collectionref.document(userid)



        documentref.get().addOnCompleteListener { task ->
            val document = task.result
            if (document.exists()) {
                data = document.data!!
            }
        }

        return data
    }
}