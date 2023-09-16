package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.MemberAdapter
import com.sih.graminshikshasahyog.databinding.FragmentCommunityBinding
import com.sih.graminshikshasahyog.model.MemberModel


class CommunityFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBinding
    private lateinit var adapter: MemberAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCommunityBinding.inflate(layoutInflater)
        adapter = MemberAdapter(
            listOf(
                MemberModel(
                    pfp = "",
                    name = "Ayush Gupta"
                ),
                MemberModel(
                    pfp = "",
                    name = "Ayush Keshri"
                ),
                MemberModel(
                    pfp = "",
                    name = "Deboborto Das"
                ),MemberModel(
                    pfp = "",
                    name = "C Varun"
                ),
                MemberModel(
                    pfp = "",
                    name = "Aditya Singh"
                )
            )
        )

        binding.rvMembers.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }
}