package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.MemberItemBinding
import com.sih.graminshikshasahyog.databinding.MentorPostItemBinding
import com.sih.graminshikshasahyog.model.MemberModel

class MemberAdapter(
    private val members: List<MemberModel>
): RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = MemberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {

        val member = members[position]
        holder.bind(member)
    }

    override fun getItemCount() = members.size

    class MemberViewHolder(private val binding: MemberItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(member: MemberModel) {
            binding.tvName.text = member.name

            Glide.with(binding.pfp.context)
                .load(member.pfp)
                .placeholder(R.drawable.user)
                .into(binding.pfp)
        }
    }
}