package com.kospavel.teamfortress2voicelines.db

import androidx.room.*

@Dao
interface SoundDao {

    @Query("SELECT * FROM soundDto")
    suspend fun getAll(): List<SoundDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sound: SoundDto)

    @Delete
    suspend fun delete(sound: SoundDto)
}