package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.MentorAdapter
import com.sih.graminshikshasahyog.core.Constants
import com.sih.graminshikshasahyog.databinding.FragmentYourMentorsBinding
import com.sih.graminshikshasahyog.model.MentorItemModel

class YourMentorsFragment : Fragment() {

    private lateinit var binding: FragmentYourMentorsBinding
    private lateinit var adapter: MentorAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYourMentorsBinding.inflate(layoutInflater)


//        adapter = MentorAdapter(
//            listOf(
//                MentorItemModel(
//                    title = "Shivam Aryan",
//                    description = "Lull",
//                    mentorId = "204030"
//                )
//            )
//        )

        binding.rvMentors.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        return binding.root
    }

}