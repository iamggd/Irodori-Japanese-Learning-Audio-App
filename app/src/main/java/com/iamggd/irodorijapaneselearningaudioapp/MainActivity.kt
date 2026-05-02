package com.iamggd.irodorijapaneselearningaudioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.iamggd.irodorijapaneselearningaudioapp.audio.ui.theme.IrodoriTheme
import com.iamggd.irodorijapaneselearningaudioapp.audio.ui.theme.ThemeMode
import com.iamggd.irodorijapaneselearningaudioapp.audio.ui.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    private lateinit var themeViewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel with application context for DataStore access
        themeViewModel = ThemeViewModel(applicationContext)

        setContent {
            // Observe the theme mode state from the ViewModel
            val themeMode by themeViewModel.themeMode.collectAsState()

            // Determine the final boolean to pass to our custom theme
            val darkTheme = when (themeMode) {
                ThemeMode.DARK -> true
                ThemeMode.LIGHT -> false
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            IrodoriTheme(darkTheme = darkTheme) {
                IrodoriNavHost(themeViewModel = themeViewModel)
            }
        }
    }
}