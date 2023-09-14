package com.sih.graminshikshasahyog.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.databinding.ActivityMainBinding
import com.sih.graminshikshasahyog.ui.fragments.AccountFragment
import com.sih.graminshikshasahyog.ui.fragments.JobsFragment
import com.sih.graminshikshasahyog.ui.fragments.MentorFragment
import com.sih.graminshikshasahyog.ui.fragments.RuralAidFragment
import com.sih.graminshikshasahyog.ui.fragments.SkillsFragment

class MainActivity : AppCompatActivity() {

    //Landing page for student sign up
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Skills"
        //Whenever the activity opens, set the skills fragment as default
        setFragment(SkillsFragment(getString(R.string.learn), getString(R.string.progress)))

        binding.bottomNav.setOnItemSelectedListener {
            //Replacing the fragments depending upon the item clicked
            when(it.itemId) {
                R.id.skills -> {
                    setFragment(SkillsFragment(getString(R.string.learn), getString(R.string.progress)))
                }
                R.id.jobs -> {
                    setFragment(JobsFragment())
                }
                R.id.mentor -> {
                    setFragment(MentorFragment(getString(R.string.find_mentors), getString(R.string.your_mentors)))
                }
                R.id.ruralAid -> {
                    setFragment(RuralAidFragment(getString(R.string.govt_schemes), getString(R.string.ngo_schemes)))
                }
                R.id.account -> {
                    setFragment(AccountFragment())
                }
                else -> {
                    setFragment(SkillsFragment(getString(R.string.learn), getString(R.string.progress)))
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