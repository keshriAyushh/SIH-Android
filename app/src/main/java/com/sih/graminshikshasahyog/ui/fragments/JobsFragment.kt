package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.JobsAdapter
import com.sih.graminshikshasahyog.databinding.FragmentJobsBinding
import com.sih.graminshikshasahyog.model.JobModel

class JobsFragment : Fragment() {

    private lateinit var binding: FragmentJobsBinding
    private lateinit var jobsAdapter: JobsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobsBinding.inflate(layoutInflater)
        jobsAdapter = JobsAdapter(
            listOf(
                JobModel(
                    title = "Teaching Assistant",
                    description = "Help teachers in guiding the students",
                    location = "Kochi",
                    company = "SRS Vidyalaya",
                    salary = "12000 INR/month",
                    jobId = "3gf674rh3u"
                ),
                JobModel(
                    title = "Teaching Assistant",
                    description = "Help teachers in guiding the students",
                    location = "Kochi",
                    company = "SRS Vidyalaya",
                    salary = "12000 INR/month",
                    jobId = "3gf674rh3u"
                ),
                JobModel(
                    title = "Teaching Assistant",
                    description = "Help teachers in guiding the students",
                    location = "Kochi",
                    company = "SRS Vidyalaya",
                    salary = "12000 INR/month",
                    jobId = "3gf674rh3u"
                ),
                JobModel(
                    title = "Teaching Assistant",
                    description = "Help teachers in guiding the students",
                    location = "Kochi",
                    company = "SRS Vidyalaya",
                    salary = "12000 INR/month",
                    jobId = "3gf674rh3u"
                ),
                JobModel(
                    title = "Teaching Assistant",
                    description = "Help teachers in guiding the students",
                    location = "Kochi",
                    company = "SRS Vidyalaya",
                    salary = "12000 INR/month",
                    jobId = "3gf674rh3u"
                ),
                JobModel(
                    title = "Teaching Assistant",
                    description = "Help teachers in guiding the students",
                    location = "Kochi",
                    company = "SRS Vidyalaya",
                    salary = "12000 INR/month",
                    jobId = "3gf674rh3u"
                ),
                JobModel(
                    title = "Teaching Assistant",
                    description = "Help teachers in guiding the students",
                    location = "Kochi",
                    company = "SRS Vidyalaya",
                    salary = "12000 INR/month",
                    jobId = "3gf674rh3u"
                ),
            )
        )

        binding.rvJobs.apply {
            adapter = jobsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }

}