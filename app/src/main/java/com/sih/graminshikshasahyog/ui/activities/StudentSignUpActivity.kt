package com.sih.graminshikshasahyog.ui.activities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.ActivityStudentSignUpBinding
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

    /*  TODO: uncomment to fetch location
        private val permissionId: Int = 2
        private lateinit var mFusedLocationClient: FusedLocationProviderClient
        private lateinit var addresses: List<Address>
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //The following code is used to change the color of "Mentor" in tvText4
        val spannable: Spannable = SpannableString(binding.tvText4.text)

        spannable.setSpan(
            ForegroundColorSpan(Color.GREEN),
            9,
            binding.tvText4.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvText4.setText(spannable, TextView.BufferType.SPANNABLE)

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
            iniValues()

            if (validateInputs()) {
                //If all inputs are valid and non empty, proceed with auth
            } else {
                Toast.makeText(this@StudentSignUpActivity, R.string.fill_all_details, Toast.LENGTH_SHORT)
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

    private fun validateInputs() =
    (name.isNotEmpty() && phone.length == 11 && email.isNotEmpty() &&
            dob.isNotEmpty() && qualification.isNotEmpty() && gender.isNotEmpty() && preference.isNotEmpty())


    private fun iniValues() {
        name = binding.etName.text.toString()
        email = binding.etEmail.text.toString()
        phone = "+91${binding.etPhone.text.toString()}"
        dob = binding.etDOB.text.toString()
        gender = binding.etGender.text.toString()
        qualification = binding.etQualification.text.toString()
        preference = if (binding.rbJobs.isChecked) "Jobs" else "Skills"
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
}