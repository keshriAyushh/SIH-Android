package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sih.graminshikshasahyog.databinding.CourseItemBinding
import com.sih.graminshikshasahyog.model.CourseModel

class CourseAdapter(
    val courseList: List<CourseModel>
): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {

        val binding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseItem: CourseModel = courseList[position]
        holder.bind(courseItem)
    }

    override fun getItemCount() = courseList.size

    class CourseViewHolder(private val binding: CourseItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(courseItem: CourseModel) {
            binding.tvCourseTitle.text = courseItem.title
            binding.tvDate.text = courseItem.dateCreated
            binding.tvAuthor.text = courseItem.author
            binding.tvDescription.text = courseItem.description
        }
    }
}