package it.kimoterru.walls

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import dagger.hilt.android.HiltAndroidApp
import it.kimoterru.walls.data.repository.PrefRepository

@HiltAndroidApp
class WallpaperApplication : Application() {

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

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }
}