package com.kospavel.teamfortress2voicelines

import android.app.Application
import android.content.Context
import com.kospavel.teamfortress2voicelines.di.AppComponent
import com.kospavel.teamfortress2voicelines.di.AppModule
import com.kospavel.teamfortress2voicelines.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    init {
        initGraph()
    }

    private fun initGraph() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        fun get(context: Context): App =
            context.applicationContext as App
    }

}