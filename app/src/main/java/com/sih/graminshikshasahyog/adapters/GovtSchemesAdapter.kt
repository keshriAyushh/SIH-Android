package com.sih.graminshikshasahyog.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sih.graminshikshasahyog.databinding.GovtSchemeItemBinding
import com.sih.graminshikshasahyog.model.GovtSchemes
import com.sih.graminshikshasahyog.ui.activities.NGO_form

class GovtSchemesAdapter(

    val schemeList: List<GovtSchemes>,
    val context: Context
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
        holder.applybtn.setOnClickListener{
            val intent = Intent(context,NGO_form::class.java)
            intent.putExtra("type","GovtScheme")
            intent.putExtra("code",holder.code)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = schemeList.size

    class SchemeViewHolder(private val binding: GovtSchemeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val applybtn = binding.btnApplyNow
        var code:String = ""
        fun bind(schemeItem: GovtSchemes) {
            binding.tvSchemeTitle.text = schemeItem.scheme
            binding.tvEligibility.text = schemeItem.desc
            code = schemeItem.code
        }
    }
}