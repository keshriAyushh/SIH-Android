package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sih.graminshikshasahyog.databinding.JobItemBinding
import com.sih.graminshikshasahyog.model.JobModel

class JobsAdapter(
    private val jobs: List<JobModel>
): RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val binding =
            JobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val jobItem: JobModel = jobs[position]
        holder.bind(jobItem)
//        holder.applybtn.setOnClickListener{
//            val intent = Intent(context, GovtSchemesForm::class.java)
//            intent.putExtra("type","GovtScheme")
//            intent.putExtra("code",holder.code)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount() = jobs.size

    class JobsViewHolder(private val binding: JobItemBinding): RecyclerView.ViewHolder(binding.root) {

        val btnApply = binding.btnApply
        fun bind(jobItem: JobModel) {
            binding.companytv.text = jobItem.company
            binding.jobdescTV.text = jobItem.description
            binding.salaryTV.text = jobItem.salary
            binding.tvLocation.text = jobItem.location
            binding.jobtitletv.text = jobItem.title
        }
    }
}