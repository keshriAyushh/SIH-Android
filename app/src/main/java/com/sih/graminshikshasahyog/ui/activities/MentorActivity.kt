package com.sih.graminshikshasahyog.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.ActivityMentorBinding
import com.sih.graminshikshasahyog.ui.fragments.AccountFragment
import com.sih.graminshikshasahyog.ui.fragments.AddPostFragment
import com.sih.graminshikshasahyog.ui.fragments.DashboardFragment
import com.sih.graminshikshasahyog.ui.fragments.JobsFragment
import com.sih.graminshikshasahyog.ui.fragments.MentorAccountFragment
import com.sih.graminshikshasahyog.ui.fragments.MentorFragment
import com.sih.graminshikshasahyog.ui.fragments.RuralAidFragment
import com.sih.graminshikshasahyog.ui.fragments.SkillsFragment

class MentorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(DashboardFragment(getString(R.string.your_communities), getString(R.string.stats)))

        binding.bottomNav.setOnItemSelectedListener {
            //Replacing the fragments depending upon the item clicked
            when(it) {
                R.id.dashboard -> {
                    setFragment(DashboardFragment(getString(R.string.your_communities), getString(R.string.stats)))
                }
                R.id.mentorAccount -> {
                    setFragment(MentorAccountFragment())
                }
                R.id.addPost -> {
                    setFragment(AddPostFragment())
                }
                else -> {
                    setFragment(DashboardFragment(getString(R.string.your_communities), getString(R.string.stats)))
                }
            }
            true
        }
    }
    private fun setFragment(fragment: Fragment) {
        //Ignore the mess, just changing the fragments
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flFragment.id, fragment)
        fragmentTransaction.commit()
    }
}