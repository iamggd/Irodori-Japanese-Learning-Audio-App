package com.iamggd.irodorijapaneselearningaudioapp.audio.ui.theme

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Extension property to create the DataStore instance
private val Context.dataStore by preferencesDataStore(name = "theme_prefs")

enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

class ThemeViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private val themeKey = stringPreferencesKey("theme_mode")

    constructor(context: Context) : this(context.dataStore)

    // Exposes the current theme state to the UI
    val themeMode: StateFlow<ThemeMode> = dataStore.data
        .map { preferences ->
            ThemeMode.valueOf(preferences[themeKey] ?: ThemeMode.SYSTEM.name)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeMode.SYSTEM
        )

    fun setTheme(mode: ThemeMode) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[themeKey] = mode.name
            }
        }
    }
}
