package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sih.graminshikshasahyog.R
import com.sih.graminshikshasahyog.adapters.ViewPagerAdapter1
import com.sih.graminshikshasahyog.adapters.ViewPagerAdapter4
import com.sih.graminshikshasahyog.databinding.FragmentDashboardBinding

class DashboardFragment: Fragment() {


    public lateinit var binding: FragmentDashboardBinding
    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter4(activity?.supportFragmentManager!!, lifecycle)
        createTabs(tabLayout, "Your communities")
        createTabs(tabLayout, "Stats")
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
        return binding.root
    }

    public fun createTabs(tabLayout: TabLayout, text: String) {
        tabLayout.addTab(tabLayout.newTab().setText(text))
    }

   public override fun onDestroyView() {
        super.onDestroyView()
    }
}