package com.kospavel.teamfortress2voicelines.repository

import android.content.Context
import com.kospavel.teamfortress2voicelines.Character
import com.kospavel.teamfortress2voicelines.R
import com.kospavel.teamfortress2voicelines.Sound
import com.kospavel.teamfortress2voicelines.db.SoundDao
import com.kospavel.teamfortress2voicelines.db.SoundDto
import com.kospavel.teamfortress2voicelines.strategies.CharacterStrategyFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val context: Context,
    private val soundDao: SoundDao
) {

    suspend fun sounds(): List<Sound> {
        val result = mutableListOf<Sound>()
        return withContext(Dispatchers.IO) {
            val favourites = soundDao.getAll().map { it.id }
            for (field in R.raw::class.java.fields) {
                val id = field.name.hashCode()
                result.add(
                    Sound(
                        id = id,
                        character = CharacterStrategyFactory.strategy(context, field.name)
                            .character() as Character,
                        resourceName = field.name,
                        favourite = id in favourites
                    )
                )
            }
            result
        }
    }

    suspend fun save(sound: Sound) {
        soundDao.insert(
            SoundDto(
                sound.id,
                sound.resourceName
            )
        )
    }

    suspend fun delete(sound: Sound) {
        soundDao.delete(
            SoundDto(
                sound.id,
                sound.resourceName
            )
        )
    }

}