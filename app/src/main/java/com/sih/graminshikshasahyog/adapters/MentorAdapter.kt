package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.MentorItemBinding
import com.sih.graminshikshasahyog.model.MentorItemModel

class MentorAdapter(
    private val mentors: List<MentorItemModel>
): RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val binding = MentorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MentorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        val mentor = mentors[position]
        holder.bind(mentor)

        holder.btnEnroll.setOnClickListener {

        }
    }

    override fun getItemCount() = mentors.size

    class MentorViewHolder(private val binding: MentorItemBinding): RecyclerView.ViewHolder(binding.root) {

        val btnEnroll = binding.btnEnroll
        fun bind(mentor: MentorItemModel) {
            binding.tvTitle.text = mentor.title
            binding.tvDescription.text = mentor.description

            Glide.with(binding.ivImage)
                .load(mentor.pfp)
                .placeholder(R.drawable.user)
                .into(binding.ivImage)
        }
    }
}