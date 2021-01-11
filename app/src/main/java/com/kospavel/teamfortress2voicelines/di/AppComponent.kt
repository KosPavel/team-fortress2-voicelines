package com.kospavel.teamfortress2voicelines.di

import com.kospavel.teamfortress2voicelines.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppDBModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)
}