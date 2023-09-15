package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.MentorPostsAdapter
import com.sih.graminshikshasahyog.databinding.FragmentMentorPostsBinding
import com.sih.graminshikshasahyog.model.MentorPostModel

class MentorPostsFragment : Fragment() {

    private lateinit var binding: FragmentMentorPostsBinding
    private lateinit var adapter: MentorPostsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorPostsBinding.inflate(layoutInflater)
        adapter = MentorPostsAdapter(
            listOf(
                MentorPostModel(
                    pfp = "",
                    date = "Posted on 01:02 pm \n" +
                            "9th September, 2023",
                    postText = "Hello everyone!\n" +
                            "\n" +
                            "I have listed out some career options for those who are passing 12th boards this year \n" +
                            "\n" +
                            "Vocational Training\n" +
                            "IT and computer skills\n" +
                            "Healthcare\n" +
                            "Teaching and education\n" +
                            "Social welfare\n" +
                            "Media\n" +
                            "\n" +
                            "Will give more details soon.\n" +
                            "\n"
                ),MentorPostModel(
                    pfp = "",
                    date = "Posted on 01:02 pm \n" +
                            "9th September, 2023",
                    postText = "Hello everyone!\n" +
                            "\n" +
                            "I have listed out some career options for those who are passing 12th boards this year \n" +
                            "\n" +
                            "Vocational Training\n" +
                            "IT and computer skills\n" +
                            "Healthcare\n" +
                            "Teaching and education\n" +
                            "Social welfare\n" +
                            "Media\n" +
                            "\n" +
                            "Will give more details soon.\n" +
                            "\n"
                ),MentorPostModel(
                    pfp = "",
                    date = "Posted on 01:02 pm \n" +
                            "9th September, 2023",
                    postText = "Hello everyone!\n" +
                            "\n" +
                            "I have listed out some career options for those who are passing 12th boards this year \n" +
                            "\n" +
                            "Vocational Training\n" +
                            "IT and computer skills\n" +
                            "Healthcare\n" +
                            "Teaching and education\n" +
                            "Social welfare\n" +
                            "Media\n" +
                            "\n" +
                            "Will give more details soon.\n" +
                            "\n"
                ),MentorPostModel(
                    pfp = "",
                    date = "Posted on 01:02 pm \n" +
                            "9th September, 2023",
                    postText = "Hello everyone!\n" +
                            "\n" +
                            "I have listed out some career options for those who are passing 12th boards this year \n" +
                            "\n" +
                            "Vocational Training\n" +
                            "IT and computer skills\n" +
                            "Healthcare\n" +
                            "Teaching and education\n" +
                            "Social welfare\n" +
                            "Media\n" +
                            "\n" +
                            "Will give more details soon.\n" +
                            "\n"
                )
            )
        )

        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

}