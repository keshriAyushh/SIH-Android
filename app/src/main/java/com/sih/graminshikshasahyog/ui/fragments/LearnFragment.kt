package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.CourseAdapter
import com.sih.graminshikshasahyog.databinding.FragmentLearnBinding
import com.sih.graminshikshasahyog.model.CourseModel


class LearnFragment : Fragment() {

    private lateinit var binding: FragmentLearnBinding
    private lateinit var courseAdapter: CourseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnBinding.inflate(layoutInflater)

        courseAdapter = CourseAdapter(
            listOf(
                CourseModel(
                    "E-library by Gramin Shiksha Sahyog",
                    "Completely free access",
                    "By Gramin Shiksha Sahyog",
                    "Created on 12th September, 2023",
                ),
                CourseModel(
                    "English Speaking Course",
                    "Beginner to Amateur level",
                    "By Shivam Aryan",
                    "Created on 8th August, 2023",
                ),
                CourseModel(
                    "Computer Course for rural youth",
                    "Beginner Level",
                    "By Smile Foundation",
                    "Created on 16th July, 2023",
                ),
                CourseModel(
                    "E-library by Gramin Shiksha Sahyog",
                    "Completely free access",
                    "By Gramin Shiksha Sahyog",
                    "Created on 12th September, 2023",
                ),
                CourseModel(
                    "English Speaking Course",
                    "Beginner to Amateur level",
                    "By Shivam Aryan",
                    "Created on 8th August, 2023",
                ),
                CourseModel(
                    "Computer Course for rural youth",
                    "Beginner Level",
                    "By Smile Foundation",
                    "Created on 16th July, 2023",
                )
            )
        )

        binding.courseRecyclerView.adapter = courseAdapter
        binding.courseRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }
}