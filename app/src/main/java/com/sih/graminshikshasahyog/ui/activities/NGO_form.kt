package com.sih.graminshikshasahyog.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R

class NGO_form : AppCompatActivity() {
    lateinit var schemetype :String
    lateinit var code :String
    lateinit var applicationform : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_form)
        val schemetype = intent.getStringExtra("type")
        val code = intent.getStringExtra("code")
        val schemename = findViewById<TextView>(R.id.namescheme)
        val shortdesc = findViewById<TextView>(R.id.shortdescscheme)
        val longdesc = findViewById<TextView>(R.id.schemedesc)
        val aboutprogramme = findViewById<TextView>(R.id.aboutprogramme)
        val requirements = findViewById<TextView>(R.id.requirements)
        val schlorashipamount = findViewById<TextView>(R.id.scholarshipamount)
        applicationform = findViewById(R.id.applicationform)

        if(schemetype.equals("NGOscheme")){
            val firestore = FirebaseFirestore.getInstance()
            val collectionReference = firestore.collection("ngoDB")
            val documentref = collectionReference.document(code.toString())
            documentref.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()){
                    val data = documentSnapshot.data
                    if (data != null) {
                        schemename.text = data.get("name").toString()
                        shortdesc.text = data.get("shortdesc").toString()
                        longdesc.text = data.get("desc").toString()
                        aboutprogramme.text = data.get("about").toString()
                        schlorashipamount.text = "Scholarship amount - " + data.get("amount")
                        val requirementsarray  = data["requirements"] as List<*>
                        var requirementstemp :String = ""
                        requirementsarray.forEachIndexed { index, any ->
                            requirementstemp = requirementstemp+(index+1).toString() + ". "+any.toString()+"\n"
                        }
                        requirements.text = requirementstemp

                    }
                }
            }

        }


    }
}