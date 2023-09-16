package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.MentorAdapter
import com.sih.graminshikshasahyog.databinding.FragmentFindMentorsBinding
import com.sih.graminshikshasahyog.model.MentorItemModel

class FindMentorsFragment : Fragment() {

    private lateinit var binding: FragmentFindMentorsBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: MentorAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFindMentorsBinding.inflate(layoutInflater)
        firestore = FirebaseFirestore.getInstance()

        adapter = MentorAdapter(
            listOf(
                MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Language Skill Development and Growth"
                ),MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Career Counselling"
                ),MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Language Skill Development and Growth"
                ),MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Career Counselling"
                ),MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Language Skill Development and Growth"
                ),MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Career Counselling"
                ),MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Language Skill Development and Growth"
                ),MentorItemModel(
                    title = "Shivam Aryan",
                    description = "Career Counselling"
                )
            )
        )

        binding.rvMentors.adapter = adapter
        binding.rvMentors.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

}