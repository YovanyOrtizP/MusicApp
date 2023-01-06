package com.example.musicdisplay.view

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.musicdisplay.R
import com.example.musicdisplay.databinding.SearchMusicBinding
import com.example.musicdisplay.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG = "SearchFragment"

class SearchMusic: Fragment() {
    private lateinit var binding: SearchMusicBinding
    private lateinit var communicator: Communicator
    private lateinit var primaryGenreName: String
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2


    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is Communicator -> communicator = context
            else -> throw Exception("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SearchMusicBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        tabLayout = binding.root.findViewById(R.id.tab_layout)
        viewPager = binding.root.findViewById(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(binding.root.context as FragmentActivity)
        TabLayoutMediator(tabLayout, viewPager){ tab,index->
            when(index){
                0 -> {
                    tab.text = "Rock"
                    tab.setIcon(R.drawable.rock_icon)
                    primaryGenreName = tab.text.toString()
                    sendSearchParams()
                }
                1 -> {
                    tab.text = "Classic"
                    tab.setIcon(R.drawable.classic_icon)
                }
                2 -> {
                    tab.text = "Pop"
                    tab.setIcon(R.drawable.pop_icon)
                }
                else -> {throw  Resources.NotFoundException("Item Not Found")}
            }
        }.attach()

        binding.apply {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                    primaryGenreName = tab.text.toString()
                    Log.d(TAG, "onTabSelected: $primaryGenreName")
                    sendSearchParams()
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {

                }
                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }

    private fun sendSearchParams() {
        communicator.sendDataToSearch(primaryGenreName)
    }
}