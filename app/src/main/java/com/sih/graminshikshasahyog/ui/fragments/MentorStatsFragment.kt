package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentMentorStatsBinding

class MentorStatsFragment : Fragment() {

    private lateinit var binding: FragmentMentorStatsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorStatsBinding.inflate(layoutInflater)


        return binding.root
    }

}