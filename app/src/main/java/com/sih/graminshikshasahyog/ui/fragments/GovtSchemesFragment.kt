package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.GovtSchemesAdapter
import com.sih.graminshikshasahyog.databinding.FragmentGovtSchemesBinding
import com.sih.graminshikshasahyog.model.GovtSchemes

class GovtSchemesFragment : Fragment() {

    private lateinit var binding: FragmentGovtSchemesBinding
    private lateinit var schemeAdapter: GovtSchemesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGovtSchemesBinding.inflate(layoutInflater)

        schemeAdapter = GovtSchemesAdapter(
            listOf(
                GovtSchemes(
                    "Ishan Uday Scholarship",
                    "For North-Eastern UG and PG students",
                ),

                GovtSchemes(
                    "AICTE Pragati Scholarship Scheme for Girls",
                    "For girls pursuing UG",
                ),
                GovtSchemes(
                    "State Merit Scholarship(SMS)",
                    "For Students graduating from Kerala"
                ),
                GovtSchemes(
                    "Rural Youth Development Program",
                "All Indian Citizens",
                )

            )

        )

        binding.schemeRecyclerView.adapter=schemeAdapter
        binding.schemeRecyclerView.layoutManager=LinearLayoutManager(requireContext())

        return binding.root

    }
}




