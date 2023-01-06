package com.example.musicdisplay.view.adapter

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicdisplay.databinding.MusicItemsBinding
import com.example.musicdisplay.model.MusicInfo
import com.squareup.picasso.Picasso


class MusicAdapter(private var dataSet: MutableList<MusicInfo>): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>(){

    private lateinit var mediaPlayer: MediaPlayer

    class MusicViewHolder(private val binding: MusicItemsBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(musicItem: MusicInfo){
            binding.apply {
                artistName.text = musicItem.artistName
                musicName.text = musicItem.trackName
                musicPrice.text = musicItem.trackPrice.toString()
                Picasso.get().load(musicItem.artworkUrl100).into(musicCover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MusicViewHolder(
        MusicItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        mediaPlayer = MediaPlayer()
        holder.onBind(dataSet[position])
        holder.itemView.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.stop()
                mediaPlayer.release()
                mediaPlayer = MediaPlayer()
            }else {
                mediaPlayer.setDataSource(dataSet[position].previewUrl)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(items: List<MusicInfo>) {
        val originalSize = dataSet.size - 1
        dataSet.addAll(items)
        notifyItemRangeInserted(originalSize, 50)
    }

}
