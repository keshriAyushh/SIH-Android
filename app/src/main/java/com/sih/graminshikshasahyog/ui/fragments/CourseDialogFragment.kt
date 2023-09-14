package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentContainer
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.FragmentLearnBinding

class CourseDialogFragment : DialogFragment() {

    private var code: String? = null
    private lateinit var binding: FragmentLearnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            code = it.getString("code")


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.courses_dialog_fragment, container, false)
        val name = view.findViewById<TextView>(R.id.coursename)
        val level = view.findViewById<TextView>(R.id.level)
        val desc = view.findViewById<TextView>(R.id.desc)
        val createdOn = view.findViewById<TextView>(R.id.createdon)
        val author = view.findViewById<TextView>(R.id.author)
        val skill1 = view.findViewById<TextView>(R.id.skill1)
        val skill2 = view.findViewById<TextView>(R.id.skill2)
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionref : CollectionReference = firestore.collection("coursesDB")
        Log.d("codedata",code.toString())
        val documentref :DocumentReference = collectionref.document(code.toString())
        documentref.get().addOnSuccessListener { documentSnapshot ->
            if(documentSnapshot.exists()){
                val data = documentSnapshot.data
                if (data != null) {
                    name.text = data.get("name").toString()
                    level.text = data.get("level").toString()
                    desc.text = data.get("description").toString()
                    createdOn.text = data.get("createdOn").toString()
                    author.text = data.get("author").toString()
                    val skillarray  = data["skills"] as List<*>
                    skill1.text = skillarray[0] as String
                    skill2.text = skillarray[1] as String


                }
            }
        }



        return view
    }
    companion object {
        fun newInstance(data: String): CourseDialogFragment {
            val fragment = CourseDialogFragment()
            val args = Bundle()
            args.putString("code", data)
            fragment.arguments = args
            return fragment
        }
    }


}