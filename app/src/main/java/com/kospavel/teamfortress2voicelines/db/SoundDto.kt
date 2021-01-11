package com.kospavel.teamfortress2voicelines.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SoundDto(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "res_name") val res_id: String
)