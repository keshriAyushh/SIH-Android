package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentAddPostBinding

class AddPostFragment : Fragment() {

    private lateinit var binding: FragmentAddPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPostBinding.inflate(layoutInflater)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if(user != null){
            val userid = user.uid.toString()
            val collectionReference = FirebaseFirestore.getInstance().collection("mentoruserDB")
            val documentref = collectionReference.document(userid)
            documentref.get()
                .addOnSuccessListener { documentSnapshot ->
                    if(documentSnapshot.exists()) {
                        val communitylist = documentSnapshot.get("communities") as? Array<*>
                        if(communitylist!=null){
                            val spinner = view?.findViewById<Spinner>(R.id.etChooseCommunity)
                            val spinnerArrayAdapter  = ArrayAdapter(requireContext(), com.bumptech.glide.R.layout.support_simple_spinner_dropdown_item,communitylist)
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // The drop-down view
                            if (spinner != null) {
                                spinner.adapter = spinnerArrayAdapter
                            }


                        }
                    }
                }


        }
        else{
            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}