package com.sih.graminshikshasahyog.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.ActivityCreateCommunityBinding
import com.sih.graminshikshasahyog.databinding.ActivityMentorSignUpBinding

class CreateCommunity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCommunityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.createcommbtn.setOnClickListener{
            storeCommunityDetails()
            Toast.makeText(this, "Button Click",Toast.LENGTH_SHORT).show()
        }

    }

    private fun storeCommunityDetails() {

        val firestoredb = FirebaseFirestore.getInstance()
        val collectionReference = firestoredb.collection("comunitydetailsDB")
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        lateinit var userId:String
        lateinit var userName : String
        if (user != null) {

             userId = user.uid

        } else {
            Toast.makeText(this, "Error user",Toast.LENGTH_SHORT).show()
        }

        val mentorCollection = firestoredb.collection("mentoruserDB")
        val mentorDocument = mentorCollection.document(userId)
        mentorDocument.get().addOnSuccessListener { documentSnapshot ->

            val data = documentSnapshot.data
            userName = data?.get("name").toString()
        }


        val data = hashMapOf(
            "communityname" to binding.etCommunityName.text,
            "visibility" to binding.etVisibility.text,
            "creator" to user,
            "bio" to binding.etBio.text,
            "shortdescription" to binding.etShortDescription.text,
            "links" to arrayListOf("")
        )
        collectionReference.document(binding.etToken.text.toString()).set(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Added communitydetailsDB",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to add communitydetailsDB",Toast.LENGTH_SHORT).show()
            }
        val subcollectionData = hashMapOf(
            "nullplace" to "null"
        )

        mentorDocument.collection(binding.etCommunityName.text.toString()).add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Added in mentorDB",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to add mentorDB",Toast.LENGTH_SHORT).show()
            }


    }



}