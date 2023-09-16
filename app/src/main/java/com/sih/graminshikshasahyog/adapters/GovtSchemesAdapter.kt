package com.sih.graminshikshasahyog.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.sih.graminshikshasahyog.databinding.GovtSchemeItemBinding
import com.sih.graminshikshasahyog.model.GovtSchemes
import com.sih.graminshikshasahyog.ui.activities.GovtSchemesForm
import com.sih.graminshikshasahyog.ui.activities.NGO_form
import com.squareup.picasso.Picasso

class GovtSchemesAdapter(

    val schemeList: List<GovtSchemes>,
    val context: Context
) : RecyclerView.Adapter<GovtSchemesAdapter.SchemeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeViewHolder {
        val binding =
            GovtSchemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: GovtSchemesAdapter.SchemeViewHolder,
        position: Int
    ) {
        val schemeItem: GovtSchemes = schemeList[position]
        holder.bind(schemeItem)
        holder.applybtn.setOnClickListener{
            val intent = Intent(context,GovtSchemesForm::class.java)
            intent.putExtra("type","GovtScheme")
            intent.putExtra("code",holder.code)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
        val storageRef = FirebaseStorage.getInstance().reference

        val imageRef = storageRef.child("govtscheme_img/00"+(position+1).toString()+".jpeg")

        imageRef.downloadUrl
            .addOnSuccessListener { uri ->
                // Use Picasso to load and display the image

                Picasso.get().load(uri).into(holder.imageview)
            }
            .addOnFailureListener { exception ->
                // Handle any errors that may occur
                println("Error downloading image: $exception")
            }
    }

    override fun getItemCount() = schemeList.size

    class SchemeViewHolder(private val binding: GovtSchemeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val applybtn = binding.btnApplyNow
        var code:String = ""
        val imageview = binding.ivScheme1
        fun bind(schemeItem: GovtSchemes) {
            binding.tvSchemeTitle.text = schemeItem.scheme
            binding.tvEligibility.text = schemeItem.desc
            code = schemeItem.code
        }
    }
}