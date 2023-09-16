package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.MentorAdapter
import com.sih.graminshikshasahyog.databinding.FragmentFindMentorsBinding
import com.sih.graminshikshasahyog.model.MentorItemModel
import kotlinx.coroutines.tasks.await

class FindMentorsFragment : Fragment() {

    private lateinit var binding: FragmentFindMentorsBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: MentorAdapter
    private lateinit var mentors: MutableList<MentorItemModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFindMentorsBinding.inflate(layoutInflater)
        firestore = FirebaseFirestore.getInstance()

        mentors = mutableListOf()

        firestore.collection("communitydetailsDB")
            .get()
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
            }

        adapter = MentorAdapter(
            mentors.toList()
        )

        binding.rvMentors.adapter = adapter
        binding.rvMentors.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

}