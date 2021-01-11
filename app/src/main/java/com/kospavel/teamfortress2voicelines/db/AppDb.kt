package com.kospavel.teamfortress2voicelines.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME = "app_db"
private const val DB_VERSION = 1

@Database(
    entities = [
        SoundDto::class
    ], version = DB_VERSION, exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun soundDao(): SoundDao

    companion object {
        fun create(context: Context): AppDb =
            Room.databaseBuilder(
                context.applicationContext,
                AppDb::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .setJournalMode(JournalMode.TRUNCATE)
                .build()
    }
}