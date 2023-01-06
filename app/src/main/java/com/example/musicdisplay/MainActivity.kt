package com.example.musicdisplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicdisplay.view.Communicator
import com.example.musicdisplay.view.DisplayMusic
import com.example.musicdisplay.view.SearchMusic

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_music, SearchMusic()).commit()
    }

    override fun sendDataToSearch(primaryGenreName: String) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_display, DisplayMusic.newInstance(primaryGenreName))
            .addToBackStack(null).commit()
    }
}
