package com.sih.graminshikshasahyog.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sih.graminshikshasahyog.databinding.CourseItemBinding
import com.sih.graminshikshasahyog.model.CourseModel
import com.sih.graminshikshasahyog.ui.fragments.CourseDialogFragment
import com.squareup.picasso.Picasso
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
        val storageRef = FirebaseStorage.getInstance().reference

            val imageRef = storageRef.child("course_img/00"+(position+1).toString()+".jpeg")

            imageRef.downloadUrl
                .addOnSuccessListener { uri ->
                    // Use Picasso to load and display the image

                    Picasso.get().load(uri).into(holder.imageview)
                }
                .addOnFailureListener { exception ->
                    // Handle any errors that may occur
                    println("Error downloading image: $exception")
                }


//        val imagePath = imagePaths[position]
//
//        // Download and display the image using Picasso
//        val storageRef = FirebaseStorage.getInstance().reference
//        loadImageFromStorage(storageRef, imagePath, holder.imageview)

    }


    override fun getItemCount() = courseList.size

    class CourseViewHolder(private val binding: CourseItemBinding): RecyclerView.ViewHolder(binding.root) {
        val applybtn = binding.btnApply
        var code:String = ""
        val imageview = binding.sivThumbnail
        fun bind(courseItem: CourseModel) {
            binding.tvCourseTitle.text = courseItem.title
            binding.tvDate.text = courseItem.dateCreated
            binding.tvAuthor.text = courseItem.author
            binding.tvDescription.text = courseItem.description


//            val imageRef = storageRef.child("course_img/image.jpg")
////            binding.sivThumbnail.resources =
            code = courseItem.code

        }
    }
}