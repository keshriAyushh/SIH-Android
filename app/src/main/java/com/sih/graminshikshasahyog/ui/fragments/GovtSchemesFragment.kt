package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.CourseAdapter
import com.sih.graminshikshasahyog.adapters.GovtSchemesAdapter
import com.sih.graminshikshasahyog.databinding.FragmentGovtSchemesBinding
import com.sih.graminshikshasahyog.databinding.FragmentLearnBinding
import com.sih.graminshikshasahyog.model.CourseModel
import com.sih.graminshikshasahyog.model.GovtSchemes

class GovtSchemesFragment : Fragment() {

    private lateinit var binding: FragmentGovtSchemesBinding
    private lateinit var schemeAdapter: GovtSchemesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val list: MutableList<GovtSchemes> = mutableListOf()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionref: CollectionReference = firestore.collection("ruralAidGovtDB")
        var courseAdapter: CourseAdapter
        binding = FragmentGovtSchemesBinding.inflate(layoutInflater)

        collectionref.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val documentId = document.id

                        val data = document.data

                        val schemeModel: GovtSchemes = GovtSchemes(
                            data.get("name").toString(),
                            data.get("shortdesc").toString(),
                            data.get("url").toString(),
                            documentId.toString()

                        )
                        list.add(schemeModel)
                    }
                    Log.d("hell", list.toString())
                    schemeAdapter = GovtSchemesAdapter(list)
                    binding.schemeRecyclerView.adapter = schemeAdapter
                    schemeAdapter.notifyDataSetChanged()
                }
            }
        binding.schemeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root

    }
}




