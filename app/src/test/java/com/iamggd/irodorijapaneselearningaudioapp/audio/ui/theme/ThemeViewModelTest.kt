package com.iamggd.irodorijapaneselearningaudioapp.audio.ui.theme

import com.iamggd.irodorijapaneselearningaudioapp.MainDispatcherRule
import java.io.File
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

@OptIn(ExperimentalCoroutinesApi::class)
class ThemeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    private val themeKey = stringPreferencesKey("theme_mode")
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var scope: CoroutineScope

    @Before
    fun setUp() {
        scope = CoroutineScope(SupervisorJob() + mainDispatcherRule.dispatcher)
        dataStore = DataStoreFactory.create(
            scope = scope,
            produceFile = { File(temporaryFolder.root, "theme_prefs_test") }
        )
    }

    @After
    fun tearDown() {
        scope.cancel()
    }

    @Test
    fun themeMode_defaults_to_system_when_empty() = runTest {
        val viewModel = ThemeViewModel(dataStore)

        val themeMode = viewModel.themeMode.first()

        assertEquals(ThemeMode.SYSTEM, themeMode)
    }

    @Test
    fun themeMode_reads_saved_value() = runTest {
        dataStore.edit { preferences ->
            preferences[themeKey] = ThemeMode.DARK.name
        }
        val viewModel = ThemeViewModel(dataStore)

        val themeMode = viewModel.themeMode.first()

        assertEquals(ThemeMode.DARK, themeMode)
    }

    @Test
    fun setTheme_updates_dataStore_and_flow() = runTest {
        val viewModel = ThemeViewModel(dataStore)

        val updatedTheme = async { viewModel.themeMode.drop(1).first() }
        viewModel.setTheme(ThemeMode.LIGHT)

        assertEquals(ThemeMode.LIGHT, updatedTheme.await())

        val storedTheme = dataStore.data.first()[themeKey]
        assertEquals(ThemeMode.LIGHT.name, storedTheme)
    }
}
