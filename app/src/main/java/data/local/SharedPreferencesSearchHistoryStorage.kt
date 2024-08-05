package data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import data.SearchHistoryStorage

class SharedPreferencesSearchHistoryStorage(
    private val prefs: SharedPreferences,
    private val gson: Gson
) : SearchHistoryStorage {

    // Реализация методов, в которых происходит чтение и запись данных в SharedPreferences

}