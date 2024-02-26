package com.example.kontrolajpc.presentation.settings

import androidx.compose.runtime.collectAsState
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kontrolajpc.applog
import com.example.kontrolajpc.util.dataStore.PreferenceKeys
import com.example.kontrolajpc.util.dataStore.UserStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewmodel @Inject constructor(
    private val dataStore: UserStore
): ViewModel() {


    val overwrite = dataStore.preferenceStateFlow.map {
        it.overwrite
    }
    val autoDelete = dataStore.preferenceStateFlow.map {
        it.autoDelete
    }

    val qcPErsonName: StateFlow<String> = dataStore.qcPersonNameFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )


    private val _settingsState = MutableStateFlow(SettingsState())

    val settingsState = _settingsState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        SettingsState()
    )

    fun onSettingsEvent(action: SettingsEvent) {
        when(action) {
            is SettingsEvent.SetDialogVisibility -> {
                _settingsState.update {
                    it.copy(
                        showDialog = action.visible
                    )
                }
            }
        }
    }
    fun readDataStoreStringToken(tokenName: Preferences.Key<String>) {
        viewModelScope.launch {
            val x = dataStore.readStringToken(tokenName).collectLatest {
            }

        }
    }
    fun writeDataStoreStringToken(tokenName: Preferences.Key<String>, tokenValue: String) {
        viewModelScope.launch {
            dataStore.saveStringToken(tokenName,tokenValue)
        }
    }
}