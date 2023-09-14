package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sih.graminshikshasahyog.databinding.GovtSchemeItemBinding
import com.sih.graminshikshasahyog.model.GovtSchemes

class GovtSchemesAdapter(

    val schemeList: List<GovtSchemes>
) : RecyclerView.Adapter<com.sih.graminshikshasahyog.adapters.GovtSchemesAdapter.SchemeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeViewHolder {
        val binding =
            GovtSchemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: com.sih.graminshikshasahyog.adapters.GovtSchemesAdapter.SchemeViewHolder,
        position: Int
    ) {
        val schemeItem: GovtSchemes = schemeList[position]
        holder.bind(schemeItem)
    }

    override fun getItemCount() = schemeList.size

    class SchemeViewHolder(private val binding: GovtSchemeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schemeItem: GovtSchemes) {
            binding.tvSchemeTitle.text = schemeItem.scheme
            binding.tvEligibility.text = schemeItem.desc
        }
    }
}