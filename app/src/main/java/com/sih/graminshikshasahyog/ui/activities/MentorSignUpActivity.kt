package com.sih.graminshikshasahyog.ui.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.ActivityMentorSignUpBinding
import com.sih.graminshikshasahyog.databinding.ActivityStudentSignUpBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MentorSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorSignUpBinding
    val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var name: String
    private lateinit var phone: String
    private lateinit var email: String
    private lateinit var qualification: String
    private lateinit var gender: String
    private lateinit var dob: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMentorSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initvalues()
        val date =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                updateLabel()
            }

        binding.etDOB.setOnClickListener {
            //Opening the date picker dialog
            DatePickerDialog(
                this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnMentorSignUp.setOnClickListener{
            if (binding.etName.text.toString().isNotEmpty()
                &&
                binding.etEmail.text.toString().isNotEmpty()
                && binding.etPassword.text.toString().isNotEmpty()
                && binding.etPhone.text.toString().length  ==  10
                &&  binding.etDOB.text.toString().isNotEmpty()
                && binding.etGender.text.toString().isNotEmpty()
                &&  binding.etMentorQualification.text.isNotEmpty()
            ){
                auth.createUserWithEmailAndPassword(binding.etEmail.text.toString(),binding.etPassword.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(this, "User auth created", Toast.LENGTH_SHORT).show()
                        create_mentorDB()

                }
            }
            else{
                Toast.makeText(this, "User auth creation failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun create_mentorDB() {
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val collectionref = firebaseFirestore.collection("mentoruserDB")
        val userid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val data = hashMapOf(
            "dob" to binding.etDOB.text.toString(),
            "email" to binding.etEmail.text.toString(),
            "gender" to binding.etGender.text.toString(),
            "name" to binding.etName.text.toString(),
            "qualification" to binding.etMentorQualification.text.toString(),
            "communities" to listOf("")
        )
        collectionref.document(userid).set(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Added details in DB", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MentorSignUpActivity, MentorActivity::class.java))
            }
            .addOnFailureListener { e->
                Toast.makeText(this, "error adding to db", Toast.LENGTH_SHORT).show()

            }
    }

    private fun initvalues() {
        auth = FirebaseAuth.getInstance()
    }
    private fun updateLabel() {
        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.UK)
        binding.etDOB.setText(dateFormat.format(myCalendar.time))
    }
}