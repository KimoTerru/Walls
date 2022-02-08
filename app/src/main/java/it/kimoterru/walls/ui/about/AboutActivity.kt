package it.kimoterru.walls.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.BuildConfig
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.ActivityAboutBinding

@AndroidEntryPoint
class AboutActivity : AppCompatActivity() {

    private val binding: ActivityAboutBinding by viewBinding()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        binding.appVersion.text = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, AboutFragment())
            .commit()
    }

    class AboutFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.about_preferences, rootKey)
        }
    }
}