package it.kimoterru.walls.data.repository

import android.content.Context
import android.content.SharedPreferences
import it.kimoterru.walls.util.PrefData.Companion.PREFERENCE_NAME
import it.kimoterru.walls.util.PrefData.Companion.PREF_MODE_NIGHT

class PrefRepository(val context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    private val editor = pref.edit()

    private fun String.put(long: Long) {
        editor.putLong(this, long)
        editor.commit()
    }

    private fun String.put(int: Int) {
        editor.putInt(this, int)
        editor.commit()
    }

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.commit()
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.getLong() = pref.getLong(this, 0)

    private fun String.getInt() = pref.getInt(this, 0)

    private fun String.getString() = pref.getString(this, "")!!

    private fun String.getBoolean() = pref.getBoolean(this, false)

    // FUNCTION FOR WORK WITH SharedPreferences
    fun getNightModeData() = PREF_MODE_NIGHT.getBoolean()

    fun setNightModeData(mode: Boolean) {
        PREF_MODE_NIGHT.put(mode)
    }

    fun clearData() {
        editor.clear()
        editor.commit()
    }
}