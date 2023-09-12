package com.sih.graminshikshasahyog.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sih.graminshikshasahyog.adapters.ViewPagerAdapter2
import com.sih.graminshikshasahyog.databinding.FragmentRuralAidBinding

class RuralAidFragment(
    private val text1: String,
    private val text2: String
) : Fragment() {

    private lateinit var binding: FragmentRuralAidBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRuralAidBinding.inflate(layoutInflater)
        val tabLayout = binding.tabLayout2
        val viewPager = binding.viewPager2

        val adapter = ViewPagerAdapter2(requireActivity().supportFragmentManager, lifecycle)
        createTabs(tabLayout, text1)
        createTabs(tabLayout, text2)
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

    private fun createTabs(tabLayout: TabLayout, text: String) {
        tabLayout.addTab(tabLayout.newTab().setText(text))
    }
}