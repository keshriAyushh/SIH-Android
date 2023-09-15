package com.sih.graminshikshasahyog.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.CommunityAdapter
import com.sih.graminshikshasahyog.databinding.FragmentMentorCommunitiesBinding
import com.sih.graminshikshasahyog.model.CommunityModel
import com.sih.graminshikshasahyog.ui.activities.CreateCommunity

class MentorCommunitiesFragment : Fragment() {

    private lateinit var binding: FragmentMentorCommunitiesBinding
    private lateinit var communityAdapter: CommunityAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorCommunitiesBinding.inflate(layoutInflater)

        communityAdapter = CommunityAdapter(
            listOf(
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                ),
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                ),
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                ),
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                ),
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                ),
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                ),
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                ),
                CommunityModel(
                    title = "English Tutorials",
                    size = "242 members enrolled",
                    visibility = "Public",
                    communityId = "2x4387cdgp2"
                )
            )
        )

        binding.rvCommunity.adapter = communityAdapter
        binding.rvCommunity.layoutManager = LinearLayoutManager(requireContext())

        binding.iconAdd.setOnClickListener{
            startActivity(Intent(requireContext(),CreateCommunity::class.java))
        }

        return binding.root
    }

}