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
import com.sih.graminshikshasahyog.adapters.CourseAdapter
import com.sih.graminshikshasahyog.databinding.FragmentFundingBinding
import com.sih.graminshikshasahyog.model.GovtSchemes


class FundingFragment : Fragment() {

    private lateinit var binding: FragmentFundingBinding
    private lateinit var schemeAdapter: NGOSchemeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val list : MutableList<GovtSchemes>  = mutableListOf()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionref : CollectionReference = firestore.collection("ngoDB")
        var courseAdapter: CourseAdapter
        binding = FragmentFundingBinding.inflate(layoutInflater)



        collectionref.get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    for (document in task.result!!){
                        val documentId = document.id

                        val data = document.data

                        val schemeModel: GovtSchemes = GovtSchemes(
                            data.get("name").toString(),
                            data.get("shortdesc").toString(),
                            "",
                            documentId


                        )
                        list.add(schemeModel)
                    }
                    Log.d("hell",list.toString())
                    schemeAdapter = NGOSchemeAdapter(list)
                    binding.fundingRecyclerView.adapter = schemeAdapter
                    schemeAdapter.notifyDataSetChanged()
                }
            }



//        schemeAdapter = GovtSchemesAdapter(
//            listOf(
//                GovtSchemes(
//                    "Ishan Uday Scholarship",
//                    "For North-Eastern UG and PG students",
//                ),
//
//                GovtSchemes(
//                    "AICTE Pragati Scholarship Scheme for Girls",
//                    "For girls pursuing UG",
//                ),
//                GovtSchemes(
//                    "State Merit Scholarship(SMS)",
//                    "For Students graduating from Kerala"
//                ),
//                GovtSchemes(
//                    "Rural Youth Development Program",
//                "All Indian Citizens",
//                )
//
//            )
//
//        )

//        binding.schemeRecyclerView.adapter=schemeAdapter
        binding.fundingRecyclerView.layoutManager=LinearLayoutManager(requireContext())

        return binding.root

    }
}




