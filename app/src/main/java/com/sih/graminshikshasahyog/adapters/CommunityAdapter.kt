package com.sih.graminshikshasahyog.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.CommunityItemBinding
import com.sih.graminshikshasahyog.databinding.GovtSchemeItemBinding
import com.sih.graminshikshasahyog.model.CommunityModel
import com.sih.graminshikshasahyog.model.GovtSchemes
import com.sih.graminshikshasahyog.ui.activities.GovtSchemesForm

class CommunityAdapter(
    private val communities: List<CommunityModel>
): RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding =
            CommunityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityAdapter.CommunityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val community: CommunityModel = communities[position]
        holder.bind(community)
    }

    override fun getItemCount() = 2

    class CommunityViewHolder(private val binding: CommunityItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(communityItem: CommunityModel) {
            binding.tvTitle.text = communityItem.title
            binding.tvSize.text = communityItem.size
            binding.tvVisibility.text = communityItem.visibility

            Glide.with(binding.ivLogo)
                .load(communityItem.logo)
                .placeholder(R.drawable.community_logo)
                .skipMemoryCache(false)//for caching the image url in case phone is offline
                .into(binding.ivLogo)
        }
    }
}