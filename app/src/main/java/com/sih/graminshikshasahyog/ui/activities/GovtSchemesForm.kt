package com.sih.graminshikshasahyog.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R

class GovtSchemesForm : AppCompatActivity() {
    lateinit var url : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_govt_schemes_form)

        val name = findViewById<TextView>(R.id.tvName)
        val eligibility = findViewById<TextView>(R.id.tvEligibility)
        val shortdesc = findViewById<TextView>(R.id.shortdescscheme)
        val document = findViewById<TextView>(R.id.tvDocuments)
        val applybtn = findViewById<TextView>(R.id.applybtn)
        val location = findViewById<TextView>(R.id.location)
        val schemetype = intent.getStringExtra("type")
        val code = intent.getStringExtra("code")

        val firestore = FirebaseFirestore.getInstance()
        val documentref = firestore.collection("ruralAidGovtDB").document(code.toString())

        documentref.get().addOnSuccessListener { documentSnapshot ->
            if(documentSnapshot.exists()){
                val data = documentSnapshot.data
                if (data != null) {
                    name.text = data.get("name").toString()
                    location.text = "By "+ data.get("location").toString()
                    shortdesc.text = data.get("shortdesc").toString()
                     url = data.get("url").toString()
                    val documentsarray  = data["documents"] as List<*>
                    var documentstemp :String = ""
                    documentsarray.forEachIndexed { index, any ->
                        documentstemp = documentstemp+(index+1).toString() + ". "+any.toString()+"\n"
                    }
                    document.text = documentstemp

                     var eligibiltytemp :String = ""
                    val eligibilityarray  = data["eligibilitydata"] as List<*>
                    eligibilityarray.forEachIndexed { index, any ->
                        eligibiltytemp = eligibiltytemp+(index+1).toString() + ". "+any.toString()+"\n"
                    }
                    document.text = eligibiltytemp



                }
            }
        }

        applybtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

   }
}