package com.sih.graminshikshasahyog.ui.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.ViewPagerAdapter1
import com.sih.graminshikshasahyog.adapters.ViewPagerAdapter5
import com.sih.graminshikshasahyog.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter5(supportFragmentManager, lifecycle)
        createTabs(tabLayout, getString(R.string.as_a_student))
        createTabs(tabLayout, getString(R.string.as_a_mentor))
        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(
            object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if(tab != null) {
                        viewPager.currentItem = tab.position
                        if(tab.text == "Mentor") {
                            tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#0CB407"))
                            tabLayout.setSelectedTabIndicatorHeight((5 * resources.displayMetrics.density).toInt())
                            tabLayout.setTabTextColors(Color.parseColor("#FF000000"), Color.parseColor("#0CB407"))
                        } else if(tab.text == "Student"){
                            tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ED8129"))
                            tabLayout.setSelectedTabIndicatorHeight((5 * resources.displayMetrics.density).toInt())
                            tabLayout.setTabTextColors(Color.parseColor("#FF000000"), Color.parseColor("#ED8129"))
                        }

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            }
        )

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
    private fun createTabs(tabLayout: TabLayout, text: String) {
        tabLayout.addTab(tabLayout.newTab().setText(text))
    }

}