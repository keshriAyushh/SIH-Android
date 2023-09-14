package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sih.graminshikshasahyog.databinding.GovtSchemeItemBinding
import com.sih.graminshikshasahyog.model.GovtSchemes
import com.sih.graminshikshasahyog.model.NGOSchemes

class NGOSchemeAdapter(

    val schemeList: List<NGOSchemes>
) : RecyclerView.Adapter<com.sih.graminshikshasahyog.adapters.NGOSchemeAdapter.SchemeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeViewHolder {
        val binding =
            GovtSchemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: com.sih.graminshikshasahyog.adapters.NGOSchemeAdapter.SchemeViewHolder,
        position: Int
    ) {
        val schemeItem: NGOSchemes = schemeList[position]
        holder.bind(schemeItem)
    }

    override fun getItemCount() = schemeList.size

    class SchemeViewHolder(private val binding: GovtSchemeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schemeItem: NGOSchemes) {
            binding.tvSchemeTitle.text = schemeItem.scheme
            binding.tvEligibility.text = schemeItem.desc
        }
    }
}