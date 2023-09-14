package com.sih.graminshikshasahyog.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.GovtSchemesAdapter
import com.sih.graminshikshasahyog.core.Constants
import com.sih.graminshikshasahyog.databinding.FragmentAccountBinding
import com.sih.graminshikshasahyog.model.GovtSchemes
import com.sih.graminshikshasahyog.model.Student
import com.sih.graminshikshasahyog.ui.activities.SignInActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Objects


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

        var student : MutableMap<String,Any> = getStudentDetails()
        Log.d("account2",student.toString())

        binding.tvName.setText(student.get("name").toString())
        binding.tvDOBValue.text = student.get("dob").toString()
        binding.tvPhoneValue.text = student.get("phone").toString()
        binding.tvGenderValue.text = student.get("gender").toString()

//        if(student.email.isNotEmpty()) {
//            binding.block3.visibility = View.GONE
//        }

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
            activity?.finish()
        }


        return binding.root
    }



    private fun currentUser() : String {
        auth = FirebaseAuth.getInstance()
        val user  = auth.currentUser
        if (user != null) {
            return user.uid
        }
        else return "null"
    }

    private fun getStudentDetails(): MutableMap<String,Any> {
        //TODO: Fetch user details here and return it to display its details
        val userid = currentUser()
        var data:MutableMap<String, Any> = mutableMapOf<String, Any>()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionref : CollectionReference = firestore.collection("studentuserDB")
        val documentref = collectionref.document(userid)

        GlobalScope.launch(Dispatchers.IO) {
            documentref.get().addOnCompleteListener { task ->
                val document = task.result

                if(document.exists()){
                    data = document.data!!
                }
            }
        }

        return data
    }
}