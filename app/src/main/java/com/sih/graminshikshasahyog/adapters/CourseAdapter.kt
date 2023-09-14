package com.sih.graminshikshasahyog.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sih.graminshikshasahyog.databinding.CourseItemBinding
import com.sih.graminshikshasahyog.model.CourseModel
import com.sih.graminshikshasahyog.ui.fragments.CourseDialogFragment
import kotlin.coroutines.coroutineContext

class CourseAdapter(
    val courseList: List<CourseModel>
): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {

        val binding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseItem = courseList[position]
        holder.bind(courseItem)
        holder.applybtn.setOnClickListener{
           val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            val dialogFragment = CourseDialogFragment.newInstance(holder.code)
            dialogFragment.show(fragmentManager,"CourseDialogFragment")
        }


    }

    override fun getItemCount() = courseList.size

    class CourseViewHolder(private val binding: CourseItemBinding): RecyclerView.ViewHolder(binding.root) {
        val applybtn = binding.btnApply
        var code:String = ""
        fun bind(courseItem: CourseModel) {
            binding.tvCourseTitle.text = courseItem.title
            binding.tvDate.text = courseItem.dateCreated
            binding.tvAuthor.text = courseItem.author
            binding.tvDescription.text = courseItem.description
            code = courseItem.code

        }
    }
}