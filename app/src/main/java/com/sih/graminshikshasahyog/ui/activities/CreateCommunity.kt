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

class CreateCommunity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_community)

        val communityname = findViewById<EditText>(R.id.etCommunityName)
        val visibilitytext = findViewById<TextView>(R.id.etVisibility)
        val token = findViewById<EditText>(R.id.etToken)
        val bio = findViewById<EditText>(R.id.etBio)
        val shortdesc = findViewById<EditText>(R.id.etShortDescription)
        val createbtn = findViewById<Button>(R.id.createcommbtn)

        createbtn.setOnClickListener{
            storeCommunityDetails(communityname.text.toString(),visibilitytext.text.toString(),bio.text.toString(),shortdesc.text.toString(),token.text.toString())

        }

    }

    private fun storeCommunityDetails(communityname: String, visibilitytext: String, bio: String, shortdesc: String, token: String) {

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
            "communityname" to communityname,
            "visibility" to visibilitytext,
            "creator" to userName,
            "bio" to bio,
            "shortdescriptio" to shortdesc,
            "links" to arrayListOf("")
        )
        collectionReference.document(token).set(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Added communitydetailsDB",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to add communitydetailsDB",Toast.LENGTH_SHORT).show()
            }
        val subcollectionData = hashMapOf(
            "nullplace" to "null"
        )

        mentorDocument.collection(communityname).add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Added in mentorDB",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to add mentorDB",Toast.LENGTH_SHORT).show()
            }


    }



}