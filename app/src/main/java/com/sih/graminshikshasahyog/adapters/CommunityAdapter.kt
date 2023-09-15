package com.sih.graminshikshasahyog.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.CommunityItemBinding
import com.sih.graminshikshasahyog.model.CommunityModel
import com.sih.graminshikshasahyog.ui.activities.MentorPostsActivity

class CommunityAdapter(
    private val communities: List<CommunityModel>,
    private val context: Context
): RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding =
            CommunityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val community: CommunityModel = communities[position]
        holder.bind(community)

        holder.btnView.setOnClickListener {
            val intent = Intent(context, MentorPostsActivity::class.java)
            intent.putExtra("communityId", community.communityId)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = communities.size

    class CommunityViewHolder(private val binding: CommunityItemBinding): RecyclerView.ViewHolder(binding.root) {

        val btnView = binding.btnView
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