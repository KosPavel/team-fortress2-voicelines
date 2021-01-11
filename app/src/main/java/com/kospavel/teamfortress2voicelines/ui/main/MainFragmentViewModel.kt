package com.kospavel.teamfortress2voicelines.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kospavel.teamfortress2voicelines.AnyCharacter
import com.kospavel.teamfortress2voicelines.Character
import com.kospavel.teamfortress2voicelines.Sound
import com.kospavel.teamfortress2voicelines.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainFragmentViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private lateinit var allSounds: List<Sound>

    private val _sounds = MutableLiveData<List<Sound>>()
    val sounds = _sounds
    val filter = MutableLiveData<Pair<AnyCharacter, String>>(Pair(AnyCharacter(), "")).apply {
        observeForever {
            if (it.first is Character) {
                _sounds.value = allSounds.filter { sound ->
                    sound.character::class == it.first::class && sound.resourceName.contains(it.second.replace(" ", "_"))
                }
            } else {
                if (this@MainFragmentViewModel::allSounds.isInitialized) {
                    _sounds.value = allSounds.filter { sound ->
                        sound.resourceName.contains(it.second.replace(" ", "_"))
                    }
                }
            }
        }
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            mainRepository.sounds().let {
                _sounds.postValue(it)
                allSounds = it
            }
        }
    }

    fun save(sound: Sound) {
        CoroutineScope(Dispatchers.IO).launch {
            if (sound.favourite) {
                mainRepository.save(sound)
            } else {
                mainRepository.delete(sound)
            }
        }
        _sounds.value?.firstOrNull {
            it.id == sound.id
        }?.apply {
            favourite = !favourite
        }
    }

}