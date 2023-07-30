package com.mj.neckdetector.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mj.neckdetector.ui.fragment.viewpager.ViewOneFragment
import com.mj.neckdetector.ui.fragment.viewpager.ViewThreeFragment
import com.mj.neckdetector.ui.fragment.viewpager.ViewTwoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ViewOneFragment()
            1 -> ViewTwoFragment()
            2 -> ViewThreeFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}