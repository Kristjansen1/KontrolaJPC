package com.example.kontrolajpc.util.dataStore

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.kontrolajpc.presentation.settings.PreferenceState
import com.example.kontrolajpc.util.dataStore.UserStore.Companion.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class UserStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("appSettings")
    }


    val preferenceStateFlow = context.dataStore.data.map { preferences ->
        val overwrite = preferences[PreferenceKeys.OVERWRITE_EXISTING_OUTPUT_FILE] ?: true
        val autoDelete = preferences[PreferenceKeys.AUTO_DELETE_EXPORTED] ?: false
        val qcPersonName = preferences[PreferenceKeys.QC_PERSON_NAME] ?: ""
        PreferenceState(overwrite,autoDelete, qcPersonName)
    }

    val qcPersonNameFlow: Flow<String> = context.dataStore.data.map {
        it[PreferenceKeys.QC_PERSON_NAME] ?: ""
    }



    fun readStringToken(userToken: Preferences.Key<String>): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[userToken] ?: ""
        }
    }
    suspend fun readStringTokenAsString(userToken: Preferences.Key<String>): String {
        return context.dataStore.data.first()[userToken] ?: ""
    }
    fun readBooleanToken(userToken: Preferences.Key<Boolean>): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[userToken] ?: false
        }
    }
     suspend fun saveStringToken(userToken: Preferences.Key<String>, tokenValue: String) {
        context.dataStore.edit { preferences ->
            preferences[userToken] = tokenValue
        }
    }
    suspend fun saveBooleanToken(userToken: Preferences.Key<Boolean>, tokenValue: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[userToken] = tokenValue
        }
    }
}