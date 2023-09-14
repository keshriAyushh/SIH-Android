package com.sih.graminshikshasahyog.ui.activities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.ActivityStudentSignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class StudentSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentSignUpBinding
    val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var name: String
    private lateinit var phone: String
    private lateinit var email: String
    private lateinit var qualification: String
    private lateinit var gender: String
    private lateinit var dob: String
    private lateinit var preference: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    /*  TODO: uncomment to fetch location
        private val permissionId: Int = 2
        private lateinit var mFusedLocationClient: FusedLocationProviderClient
        private lateinit var addresses: List<Address>
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initValues()
        //The following code is used to change the color of "Mentor" in tvText4
        val spannable: Spannable = SpannableString(binding.tvText4.text)

        spannable.setSpan(
            ForegroundColorSpan(Color.GREEN),
            9,
            binding.tvText4.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvText4.setText(spannable, TextView.BufferType.SPANNABLE)

        binding.tvRedirect.setOnClickListener {
            startActivity(Intent(this@StudentSignUpActivity, SignInActivity::class.java))
            finish()
        }

        //Getting the date of birth in desired format
        val date =
            OnDateSetListener { view, year, month, day ->
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

        binding.btnSignUp.setOnClickListener {

            if (binding.etName.text.toString().isNotEmpty()
                    &&
                binding.etEmail.text.toString().isNotEmpty()
                && binding.etPassword.text.toString().isNotEmpty()
                && binding.etPhone.text.toString().length  ==  10
                &&  binding.etDOB.text.toString().isNotEmpty()
                && binding.etGender.text.toString().isNotEmpty()
                &&  binding.etQualification.text.isNotEmpty()
            ) {
                name = binding.etName.text.toString()
                email = binding.etEmail.text.toString()
                password = binding.etPassword.text.toString()
                phone = "+91${binding.etPhone.text.toString()}"
                dob = binding.etDOB.text.toString()
                gender = binding.etGender.text.toString()
                qualification = binding.etQualification.text.toString()
                preference = if (binding.rbJobs.isChecked) "Jobs" else "Skills"

                if(preference.isNotEmpty()) {
                    GlobalScope.launch(Dispatchers.IO) {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener {
                                createUserDB(
                                    email = email, password = password, name = name, phone =  phone, dob = dob, preference = preference, gender = gender, qualification = qualification
                                )
                                startActivity(Intent(this@StudentSignUpActivity, MainActivity::class.java))
                            }
                            .addOnFailureListener {
                                Log.d("AuthError", "Auth failed!")
                            }
                    }
                }


            } else {
                Toast.makeText(
                    this@StudentSignUpActivity,
                    R.string.fill_all_details,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        /*
                TODO: Uncomment to fetch location
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

                binding.btnFetchLocation.setOnClickListener {
                    getLocation()

                }
         */


    } //onCreate()

    private fun createUserDB(
        name: String,
        email: String,
        password: String,
        qualification: String,
        gender: String,
        dob: String,
        preference: String,
        phone: String
    ) {


        val data = hashMapOf(
            "name" to name,
            "phone" to phone,
            "email" to email,
            "qualification" to qualification,
            "gender" to gender,
            "dob" to dob,
            "looking for" to preference,
            "profilePicture" to "",
            "userId" to auth.currentUser?.uid
        )

        GlobalScope.launch(Dispatchers.IO) {
            firestore.collection("studentuserDB").document(auth.currentUser?.uid!!).set(data)
                .addOnSuccessListener { documentReference ->
                    Log.d("Save success", "Data saved!")
                }
                .addOnFailureListener { e ->
                    Log.d("Save failed", "Data not saved!")
                }
        }
    }

    private fun initValues() {
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
    }

    private fun updateLabel() {
        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.UK)
        binding.etDOB.setText(dateFormat.format(myCalendar.time))
    }
    /*
        TODO: Uncomment to fetch location
        @SuppressLint("MissingPermission")
        private fun getLocation() {
            if (checkPermissions()) {
                if (isLocationEnabled()) {
                    mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                        val location: Location? = task.result
                        if (location != null) {
                            val geocoder = Geocoder(this, Locale.getDefault())
                            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
                            binding.tvAddress.text = addresses[0].locality
                        }
                    }
                } else {
                    Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }
            } else {
                requestPermissions()
            }

        }

        private fun checkPermissions(): Boolean {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            }
            return false
        }

        private fun isLocationEnabled(): Boolean {
            val locationManager: LocationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        }

        private fun requestPermissions() {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                permissionId
            )
        }

        @SuppressLint("MissingSuperCall")
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
        ) {
            if (requestCode == permissionId) {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLocation()
                }
            }
        }
    */

    override fun onStart() {
        super.onStart()

        if(FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this@StudentSignUpActivity, MainActivity::class.java))
            finish()
        }
    }
}
