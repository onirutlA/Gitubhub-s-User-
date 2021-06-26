package com.onirutla.githubsuser.view.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.onirutla.githubsuser.view.detail.follower.FollowerFragment
import com.onirutla.githubsuser.view.detail.following.FollowingFragment

class PagerAdapter(f: Fragment, private val username: String): FragmentStateAdapter(f) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FollowerFragment(username)
            1 -> FollowingFragment(username)
            else -> Fragment()
        }
    }

}