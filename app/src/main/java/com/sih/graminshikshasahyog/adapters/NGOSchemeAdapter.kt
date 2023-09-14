package com.sih.graminshikshasahyog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sih.graminshikshasahyog.databinding.GovtSchemeItemBinding
import com.sih.graminshikshasahyog.databinding.NgoItemBinding
import com.sih.graminshikshasahyog.model.GovtSchemes
import com.sih.graminshikshasahyog.model.NGOSchemes

class NGOSchemeAdapter(
    private val ngoSchemesList: List<NGOSchemes>
): RecyclerView.Adapter<NGOSchemeAdapter.SchemeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeViewHolder {
        val binding =
            NgoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: com.sih.graminshikshasahyog.adapters.NGOSchemeAdapter.SchemeViewHolder,
        position: Int
    ) {
        val ngoSchemeItem: NGOSchemes = ngoSchemesList[position]
        holder.bind(ngoSchemeItem)
    }

    override fun getItemCount() = ngoSchemesList.size

    class SchemeViewHolder(private val binding: NgoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ngoSchemeItem: NGOSchemes) {
            binding.tvNGO.text = ngoSchemeItem.scheme
            binding.tvType.text = ngoSchemeItem.desc
        }
    }
}