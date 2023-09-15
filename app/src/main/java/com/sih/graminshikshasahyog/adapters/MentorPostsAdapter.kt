package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.MentorPostItemBinding
import com.sih.graminshikshasahyog.model.JobModel
import com.sih.graminshikshasahyog.model.MentorPostModel

class MentorPostsAdapter(
    val posts: List<MentorPostModel>
): RecyclerView.Adapter<MentorPostsAdapter.MentorPostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorPostsViewHolder {
        val binding =
            MentorPostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MentorPostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorPostsViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount() = posts.size

    class MentorPostsViewHolder(private val binding: MentorPostItemBinding): RecyclerView.ViewHolder(binding.root) {

        val btnThank = binding.btnThank
        val btnReply = binding.btnReply
        val btnDelete = binding.btnDelete
        fun bind(post: MentorPostModel) {
            binding.tvPostText.text = post.postText
            binding.tvDate.text = post.date

            Glide.with(binding.profilePicture.context)
                .load(post.pfp)
                .placeholder(R.drawable.user)
                .into(binding.profilePicture)
        }
    }
}