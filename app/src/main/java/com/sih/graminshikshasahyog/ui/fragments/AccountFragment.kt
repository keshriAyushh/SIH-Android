package com.sih.graminshikshasahyog.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentAccountBinding
import com.sih.graminshikshasahyog.ui.activities.SignInActivity
import java.util.Locale


class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private val permissionId: Int = 2
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var addresses: List<Address>
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
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
            }

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
            activity?.finish()
        }

        binding.block2.setOnClickListener {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            getLocation()
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

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        val geocoder = Geocoder(requireContext(), Locale.getDefault())
                        addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>

                        binding.tvText3.text = "${addresses[0].locality}, ${addresses[0].countryName}"
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }

    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
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
}