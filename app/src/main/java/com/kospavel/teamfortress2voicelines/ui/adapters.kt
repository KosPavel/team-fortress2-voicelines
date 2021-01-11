package com.kospavel.teamfortress2voicelines.ui

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.kospavel.teamfortress2voicelines.NoSounds
import com.kospavel.teamfortress2voicelines.R
import com.kospavel.teamfortress2voicelines.Sound
import kotlinx.android.synthetic.main.item_sound_view.view.*

class MainAdapter(private val save: (Sound) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<Any>()
    private lateinit var mp: MediaPlayer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val layout =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_sound_view, parent, false) as LinearLayout
                SoundViewHolder(layout)
            }
            2 -> {
                val layout =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_nosounds_view, parent, false) as LinearLayout
                NoSoundsViewHolder(layout)
            }
            else -> {
                val layout =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_error_view, parent, false) as LinearLayout
                UnknownDataViewHolder(layout)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 1) {
            holder.itemView.apply {
                val sound = items[position] as Sound
                item_tv.text = sound.resourceName
                item_tv.isSelected = true
                favourite_cb.isChecked = sound.favourite
                portrait.setImageResource(sound.character.portrait)
                favourite_cb.setOnClickListener {
                    save(sound)
                }
                sound_cardview.setOnClickListener {
                    if (this@MainAdapter::mp.isInitialized && mp.isPlaying) {
                        mp.stop()
                    }
                    mp =
                        MediaPlayer.create(
                            holder.itemView.item_tv.context,
                            holder.itemView.item_tv.context.resources.getIdentifier(
                                sound.resourceName,
                                "raw",
                                holder.itemView.item_tv.context.packageName
                            )
                        )
                    mp.start()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Sound -> 1
            is NoSounds -> 2
            else -> 0
        }
    }

    override fun getItemCount(): Int = items.size

    class SoundViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
    class NoSoundsViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
    class UnknownDataViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)

}