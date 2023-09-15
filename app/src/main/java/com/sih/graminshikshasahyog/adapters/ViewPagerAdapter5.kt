package com.sih.graminshikshasahyog.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sih.graminshikshasahyog.ui.fragments.MentorCommunitiesFragment
import com.sih.graminshikshasahyog.ui.fragments.MentorSignInFragment
import com.sih.graminshikshasahyog.ui.fragments.MentorStatsFragment
import com.sih.graminshikshasahyog.ui.fragments.StudentSignInFragment

class ViewPagerAdapter5(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(
    fragmentManager, lifecycle
) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                StudentSignInFragment()
            }

            1 -> {
                MentorSignInFragment()
            }

            else -> {
                StudentSignInFragment()
            }
        }
    }
}