package com.sih.graminshikshasahyog.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sih.graminshikshasahyog.ui.fragments.CommunityFragment
import com.sih.graminshikshasahyog.ui.fragments.MentorPostsFragment

class ViewPagerAdapter6(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(
    fragmentManager, lifecycle
) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MentorPostsFragment()
            }

            1 -> {
                CommunityFragment()
            }

            else -> {
                MentorPostsFragment()
            }
        }
    }
}