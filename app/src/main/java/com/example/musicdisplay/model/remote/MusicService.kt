package com.example.musicdisplay.model.remote

import com.example.musicdisplay.model.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {
    //https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
    @GET(SEARCH)
    fun getMusic(
        @Query(TERM) primaryGenreName:String,
    ):Call<MusicResponse>
}