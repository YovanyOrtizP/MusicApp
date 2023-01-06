package com.example.musicdisplay.model

class MusicResponse (
    val resultCount: Int,
    val results: List<MusicInfo>,
)

data class MusicInfo(
    val artistName: String,
    val trackName: String,
    val trackPrice: Double,
    val previewUrl: String,
    val artworkUrl100: String,
    val primaryGenreName: String
)
