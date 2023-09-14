package com.sih.graminshikshasahyog.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sih.graminshikshasahyog.ui.fragments.FindMentorsFragment
import com.sih.graminshikshasahyog.ui.fragments.FundingFragment
import com.sih.graminshikshasahyog.ui.fragments.GovtSchemesFragment
import com.sih.graminshikshasahyog.ui.fragments.YourMentorsFragment

class ViewPagerAdapter3(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(
    fragmentManager, lifecycle
) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FindMentorsFragment()
            }

            1 -> {
                YourMentorsFragment()
            }

            else -> {
                FindMentorsFragment()
            }
        }
    }
}