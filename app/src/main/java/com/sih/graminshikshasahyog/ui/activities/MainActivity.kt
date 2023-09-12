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
        setFragment(SkillsFragment("Learn", "Progress"))

        binding.bottomNav.setOnItemSelectedListener {
            //Replacing the fragments depending upon the item clicked
            when(it.itemId) {
                R.id.skills -> {
                    setFragment(SkillsFragment("Learn", "Progress"))
                    supportActionBar?.title = "Skills"
                }
                R.id.jobs -> {
                    setFragment(JobsFragment())
                    supportActionBar?.title = "Jobs"
                }
                R.id.mentor -> {
                    setFragment(MentorFragment())
                    supportActionBar?.title = "Mentor"
                }
                R.id.ruralAid -> {
                    setFragment(RuralAidFragment("Govt. Schemes", "NGO Funding"))
                    supportActionBar?.title = "Rural Aid"
                }
                R.id.account -> {
                    setFragment(AccountFragment())
                    supportActionBar?.title = "Account"
                }
                else -> {
                    setFragment(SkillsFragment("Learn", "Progress"))
                    supportActionBar?.title = "Skills"
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