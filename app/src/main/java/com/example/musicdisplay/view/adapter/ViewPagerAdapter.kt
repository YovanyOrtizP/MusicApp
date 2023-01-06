package com.example.musicdisplay.view.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicdisplay.view.DisplayMusic


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    //We are going to have only 3 tabs
    override fun getItemCount() = 3

    //create the functionality for each tab
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {DisplayMusic()}
            1 -> {DisplayMusic()}
            2 -> {DisplayMusic()}
            else -> {throw Resources.NotFoundException("Item Not Found")}
        }
    }

}
