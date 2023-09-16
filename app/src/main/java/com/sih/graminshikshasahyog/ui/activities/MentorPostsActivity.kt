package com.sih.graminshikshasahyog.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.ViewPagerAdapter1
import com.sih.graminshikshasahyog.adapters.ViewPagerAdapter6
import com.sih.graminshikshasahyog.databinding.ActivityMentorPostsBinding

class MentorPostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter6(supportFragmentManager, lifecycle)
        createTabs(tabLayout, getString(R.string.mentor_posts))
        createTabs(tabLayout, getString(R.string.community))
        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(
            object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if(tab != null) {
                        viewPager.currentItem = tab.position
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