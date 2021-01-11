package com.kospavel.teamfortress2voicelines.di

import android.content.Context
import com.kospavel.teamfortress2voicelines.db.AppDb
import com.kospavel.teamfortress2voicelines.db.SoundDao
import com.kospavel.teamfortress2voicelines.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDBModule {

    @Singleton
    @Provides
    fun db(c: Context): AppDb = AppDb.create(c)

    @Singleton
    @Provides
    fun soundDao(db: AppDb): SoundDao = db.soundDao()

    @Singleton
    @Provides
    fun mainRepository(soundDao: SoundDao, c: Context): MainRepository = MainRepository(c, soundDao)
}