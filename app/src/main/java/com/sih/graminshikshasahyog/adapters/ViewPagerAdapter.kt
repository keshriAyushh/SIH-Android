package com.sih.graminshikshasahyog.adapters

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sih.graminshikshasahyog.ui.fragments.LearnFragment
import com.sih.graminshikshasahyog.ui.fragments.ProgressFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle
): FragmentStateAdapter(
    fragmentManager, lifecycle
) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                LearnFragment()
            }
            1 -> {
                ProgressFragment()
            }
            else -> {
                LearnFragment()
            }
        }
    }
}