package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.CourseAdapter
import com.sih.graminshikshasahyog.adapters.JobsAdapter
import com.sih.graminshikshasahyog.adapters.NGOSchemeAdapter
import com.sih.graminshikshasahyog.databinding.FragmentJobsBinding
import com.sih.graminshikshasahyog.model.JobModel
import com.sih.graminshikshasahyog.model.NGOSchemes

class JobsFragment : Fragment() {

    private lateinit var binding: FragmentJobsBinding
    private lateinit var jobsAdapter: JobsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobsBinding.inflate(layoutInflater)

        val list : MutableList<JobModel>  = mutableListOf()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionref : CollectionReference = firestore.collection("jobDB")
        var jobsAdapter: JobsAdapter

        collectionref.get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    for (document in task.result!!){
                        val documentId = document.id

                        val data = document.data
                        val requirementsarray  = data["requirements"] as List<*>
                        var requirementstemp :String = ""
                        requirementsarray.forEachIndexed { index, any ->
                            requirementstemp = requirementstemp+(index+1).toString() + ". "+any.toString()+"\n"
                        }


                        val schemeModel: JobModel = JobModel(
                            data.get("jobTitle").toString(),
                            data.get("desc").toString(),
                            documentId.toString(),
                            data.get("company").toString(),
                            data.get("salary").toString(),
                            data.get("timings").toString(),
                            requirementstemp


                        )
                        list.add(schemeModel)
                    }
                    Log.d("hell",list.toString())
                    jobsAdapter = JobsAdapter(list)
                    binding.rvJobs.adapter = jobsAdapter
                    jobsAdapter.notifyDataSetChanged()
                }
            }

//        jobsAdapter = JobsAdapter(
//            listOf(
//                JobModel(
//                    title = "Teaching Assistant",
//                    description = "Help teachers in guiding the students",
//                    location = "Kochi",
//                    company = "SRS Vidyalaya",
//                    salary = "12000 INR/month",
//                    jobId = "3gf674rh3u"
//                ),
//                JobModel(
//                    title = "Teaching Assistant",
//                    description = "Help teachers in guiding the students",
//                    location = "Kochi",
//                    company = "SRS Vidyalaya",
//                    salary = "12000 INR/month",
//                    jobId = "3gf674rh3u"
//                ),
//                JobModel(
//                    title = "Teaching Assistant",
//                    description = "Help teachers in guiding the students",
//                    location = "Kochi",
//                    company = "SRS Vidyalaya",
//                    salary = "12000 INR/month",
//                    jobId = "3gf674rh3u"
//                ),
//                JobModel(
//                    title = "Teaching Assistant",
//                    description = "Help teachers in guiding the students",
//                    location = "Kochi",
//                    company = "SRS Vidyalaya",
//                    salary = "12000 INR/month",
//                    jobId = "3gf674rh3u"
//                ),
//                JobModel(
//                    title = "Teaching Assistant",
//                    description = "Help teachers in guiding the students",
//                    location = "Kochi",
//                    company = "SRS Vidyalaya",
//                    salary = "12000 INR/month",
//                    jobId = "3gf674rh3u"
//                ),
//                JobModel(
//                    title = "Teaching Assistant",
//                    description = "Help teachers in guiding the students",
//                    location = "Kochi",
//                    company = "SRS Vidyalaya",
//                    salary = "12000 INR/month",
//                    jobId = "3gf674rh3u"
//                ),
//                JobModel(
//                    title = "Teaching Assistant",
//                    description = "Help teachers in guiding the students",
//                    location = "Kochi",
//                    company = "SRS Vidyalaya",
//                    salary = "12000 INR/month",
//                    jobId = "3gf674rh3u"
//                ),
//            )
//        )

//        binding.rvJobs.apply {
//            adapter = jobsAdapter
//            layoutManager = LinearLayoutManager(requireContext())
//        }
        binding.rvJobs.layoutManager=LinearLayoutManager(requireContext())
        return binding.root
    }

}