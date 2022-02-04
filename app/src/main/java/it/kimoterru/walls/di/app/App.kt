package it.kimoterru.walls.di.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import it.kimoterru.walls.data.repository.PrefRepository

@HiltAndroidApp
class App: Application() {

    private val prefRepository by lazy { PrefRepository(applicationContext) }

    override fun onCreate() {
        super.onCreate()
        checkNM()
    }

    private fun checkNM() {
        if (prefRepository.getNightModeData()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}